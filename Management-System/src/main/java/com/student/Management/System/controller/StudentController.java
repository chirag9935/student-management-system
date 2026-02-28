package com.student.Management.System.controller;

import com.student.Management.System.entity.Student;
import com.student.Management.System.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API Controller — exposes JSON endpoints for external integrations.
 *
 * Base URL: /api/students
 *
 * This is separate from ViewController which serves HTML pages.
 * REST controllers return JSON data that can be consumed by mobile apps,
 * frontend frameworks like React/Angular, or testing tools like Postman.
 *
 * All endpoints require authentication (configured in SecurityConfig).
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * GET /api/students
     * Returns all students sorted alphabetically by name.
     * Supports optional search: GET /api/students?search=chirag
     */
    @GetMapping
    public List<Student> getAllStudents(@RequestParam(required = false) String search) {
        if (search != null && !search.isBlank()) {
            return service.searchStudents(search);
        }
        return service.getAllStudents();
    }

    /**
     * GET /api/students/{id}
     * Returns a single student by ID.
     * Returns 404 if not found (handled by GlobalExceptionHandler).
     */
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    /**
     * POST /api/students
     * Creates a new student record.
     * @Valid triggers validation annotations on the Student entity.
     * Returns 201 Created on success.
     */
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) {
        Student saved = service.addStudents(student);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * PUT /api/students/{id}
     * Updates an existing student's details.
     * Returns 404 if the student doesn't exist.
     */
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody @Valid Student student) {
        return service.updateStudent(id, student);
    }

    /**
     * DELETE /api/students/{id}
     * Deletes a student by ID.
     * Returns 204 No Content on success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
