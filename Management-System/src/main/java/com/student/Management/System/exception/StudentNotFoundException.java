package com.student.Management.System.exception;

/**
 * Exception thrown when a student record is not found in the database.
 * This is a custom unchecked exception that extends RuntimeException.
 */
public class StudentNotFoundException extends RuntimeException {

    private final Long studentId;

    public StudentNotFoundException(Long id) {
        super("Student not found with ID: " + id);
        this.studentId = id;
    }

    public Long getStudentId() {
        return studentId;
    }
}
