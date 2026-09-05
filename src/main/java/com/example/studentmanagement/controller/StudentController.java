package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(
        name = "Student Management",
        description = "REST APIs for managing student records"
)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @Operation(
            summary = "Create a student",
            description = "Creates a new student record in the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid student data"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists"
            )
    })
    @PostMapping
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = studentService.addStudent(student);

        return ResponseEntity
                .status(201)
                .body(savedStudent);
    }

    // READ ALL WITH PAGINATION
    @Operation(
            summary = "Get all students",
            description = "Returns students using page number and page size"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Students retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                studentService.getAllStudents(page, size)
        );
    }

    // READ ONE
    @Operation(
            summary = "Get student by ID",
            description = "Returns a student using the student's ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SEARCH BY NAME
    @Operation(
            summary = "Search students by name",
            description = "Searches students using a partial or complete name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Search completed successfully"
            )
    })
    @GetMapping("/search/name")
    public ResponseEntity<List<Student>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                studentService.searchByName(name)
        );
    }

    // SEARCH BY COURSE
    @Operation(
            summary = "Search students by course",
            description = "Searches students using a partial or complete course name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Search completed successfully"
            )
    })
    @GetMapping("/search/course")
    public ResponseEntity<List<Student>> searchByCourse(
            @RequestParam String course) {

        return ResponseEntity.ok(
                studentService.searchByCourse(course)
        );
    }

    // UPDATE
    @Operation(
            summary = "Update a student",
            description = "Updates an existing student's information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid student data"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        Student updatedStudent =
                studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE
    @Operation(
            summary = "Delete a student",
            description = "Deletes a student using the student's ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }
}