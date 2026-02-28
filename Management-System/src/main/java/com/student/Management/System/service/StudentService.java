package com.student.Management.System.service;

import com.student.Management.System.entity.Student;
import com.student.Management.System.exception.StudentNotFoundException;
import com.student.Management.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Service layer for Student CRUD operations and business logic.
 * This class sits between the Controller and Repository.
 */
@Service
public class StudentService {

    private final StudentRepository repo;

    // Constructor injection (preferred over @Autowired on fields for testability)
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    /** Returns all students, sorted by name alphabetically. */
    public List<Student> getAllStudents() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    /**
     * Finds a single student by ID.
     * Throws StudentNotFoundException if not found (instead of returning null).
     */
    public Student getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    /** Saves a new student to the database. */
    public Student addStudents(Student student) {
        return repo.save(student);
    }

    /**
     * Updates an existing student's details.
     * Fetches the existing record first to avoid overwriting ID or other managed fields.
     */
    public Student updateStudent(Long id, Student studentDetails) {
        Student existing = getStudentById(id); // throws if not found
        existing.setName(studentDetails.getName());
        existing.setEmail(studentDetails.getEmail());
        existing.setCourse(studentDetails.getCourse());
        existing.setMarks(studentDetails.getMarks());
        existing.setPhone(studentDetails.getPhone());
        existing.setEnrolledDate(studentDetails.getEnrolledDate());
        return repo.save(existing);
    }

    /** Deletes a student by ID. Throws if not found. */
    public void deleteStudent(Long id) {
        getStudentById(id); // validate existence first
        repo.deleteById(id);
    }

    /**
     * Searches students by keyword across name, email, and course.
     * Returns all students if keyword is empty/null.
     */
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllStudents();
        }
        return repo.searchStudents(keyword.trim());
    }

    // ─── Statistics helpers ──────────────────────────────────────────

    /** Returns the total number of students enrolled. */
    public long getTotalStudents() {
        return repo.count();
    }

    /** Returns the average marks across all students, or 0.0 if no students exist. */
    public double getAverageMarks() {
        OptionalDouble avg = repo.findAll().stream()
                .filter(s -> s.getMarks() != null)
                .mapToDouble(Student::getMarks)
                .average();
        return avg.isPresent() ? Math.round(avg.getAsDouble() * 10.0) / 10.0 : 0.0;
    }

    /** Returns the student with the highest marks, or null if none. */
    public Student getTopStudent() {
        return repo.findAll().stream()
                .filter(s -> s.getMarks() != null)
                .max(Comparator.comparingDouble(Student::getMarks))
                .orElse(null);
    }

    /** Returns the percentage of students who scored 50 or above (passing marks). */
    public double getPassingRate() {
        List<Student> all = repo.findAll();
        if (all.isEmpty()) return 0.0;
        long passing = all.stream().filter(s -> s.getMarks() != null && s.getMarks() >= 50).count();
        return Math.round((passing * 100.0 / all.size()) * 10.0) / 10.0;
    }
}
