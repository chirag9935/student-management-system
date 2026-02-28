package com.student.Management.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Student entity representing a student record in the system.
 * Fields include personal info, course, marks, and enrollment date.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Course is required")
    @Size(max = 100, message = "Course name must be at most 100 characters")
    private String course;

    @NotNull(message = "Marks are required")
    @Min(value = 0, message = "Marks cannot be negative")
    @Max(value = 100, message = "Marks cannot exceed 100")
    private Double marks;

    @Pattern(
        regexp = "^$|^[0-9+ \\-]{7,15}$",
        message = "Phone number must be 7–15 digits (digits, spaces, +, - allowed)"
    )
    private String phone;

    @Column(name = "enrolled_date")
    private LocalDate enrolledDate;

    // ─── Constructors ───────────────────────────────────────────────

    public Student() {
        this.enrolledDate = LocalDate.now();
    }

    // ─── Computed Properties ────────────────────────────────────────

    /**
     * Returns a letter grade based on marks.
     * A: 90–100, B: 75–89, C: 60–74, D: 50–59, F: below 50
     */
    public String getGrade() {
        if (marks == null) return "N/A";
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    /**
     * Returns a CSS class for the grade badge coloring.
     */
    public String getGradeClass() {
        if (marks == null) return "grade-na";
        if (marks >= 90) return "grade-a";
        if (marks >= 75) return "grade-b";
        if (marks >= 60) return "grade-c";
        if (marks >= 50) return "grade-d";
        return "grade-f";
    }

    // ─── Getters & Setters ──────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getEnrolledDate() { return enrolledDate; }
    public void setEnrolledDate(LocalDate enrolledDate) { this.enrolledDate = enrolledDate; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', email='" + email + "', course='" + course + "', marks=" + marks + "}";
    }
}
