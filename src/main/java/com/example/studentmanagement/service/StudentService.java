package com.example.studentmanagement.service;

import com.example.studentmanagement.exception.DuplicateEmailException;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student addStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateEmailException(
                    "Student with email already exists: " + student.getEmail()
            );
        }

        return studentRepository.save(student);
    }

    // READ ALL WITH PAGINATION
    public Page<Student> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    // READ ONE
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // UPDATE
    public Student updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());

        return studentRepository.save(student);
    }

    // DELETE
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        studentRepository.delete(student);
    }

    // SEARCH BY NAME
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // SEARCH BY COURSE
    public List<Student> searchByCourse(String course) {
        return studentRepository.findByCourseContainingIgnoreCase(course);
    }
}