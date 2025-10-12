package com.student.Management.System.service;

import com.student.Management.System.entity.Student;
import com.student.Management.System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student addStudents(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> existingStudent = repo.findById(id);
        if(existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            student.setCourse(studentDetails.getCourse());
            student.setMarks(studentDetails.getMarks());
            return repo.save(student);
        }else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
