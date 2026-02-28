package com.student.Management.System.repository;

import com.student.Management.System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Student entity.
 * Extends JpaRepository to get CRUD operations for free.
 * Also includes a custom search method across multiple fields.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Search students by name, email, or course (case-insensitive).
     * Uses JPQL (Java Persistence Query Language) — a SQL-like language for JPA entities.
     *
     * @param keyword the search term
     * @return a list of matching students
     */
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.course) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchStudents(@Param("keyword") String keyword);
}
