package com.student.Management.System.controller;

import com.student.Management.System.entity.Student;
import org.springframework.ui.Model;
import com.student.Management.System.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    private StudentService studentService;

    public ViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String viewDashboard(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getAllStudents()
                .stream().filter(s -> s.getId().equals(id)).findFirst().orElse(new Student()));
        return "student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        if (student.getId() == null) studentService.addStudents(student);
        else studentService.updateStudent(student.getId(), student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // login.html
    }
}
