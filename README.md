# Student Management System

A RESTful Student Management System built using Spring Boot, Spring Data JPA, Hibernate, and MySQL.

## Tech Stack

- Java 17
- Spring Boot 3.5.5
- Spring Data JPA
- Hibernate
- MySQL
- REST API
- Swagger / OpenAPI
- Maven

## Features

- Create new student records
- Get all students with pagination
- Get student by ID
- Update student details
- Delete student records
- Search students by name
- Search students by course
- Input validation
- Duplicate email checking
- Global exception handling
- Automatic database table creation using Hibernate
- Interactive API documentation using Swagger / OpenAPI

## Project Structure

```text
src/main/java/com/example/studentmanagement
│
├── controller
│   └── StudentController.java
│
├── dto
│   ├── StudentRequestDTO.java
│   └── StudentResponseDTO.java
│
├── entity
│   └── Student.java
│
├── exception
│   ├── DuplicateEmailException.java
│   ├── StudentNotFoundException.java
│   └── GlobalExceptionHandler.java
│
├── repository
│   └── StudentRepository.java
│
├── service
│   └── StudentService.java
│
└── StudentManagementApplication.java
