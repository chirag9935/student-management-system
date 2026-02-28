package com.student.Management.System.controller;

import com.student.Management.System.entity.Student;
import com.student.Management.System.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Main web controller — handles URL routes and renders Thymeleaf HTML views.
 *
 * Why separate from StudentController (REST)?
 * This controller returns HTML pages (for humans browsing the app),
 * while StudentController returns JSON (for APIs / frontend frameworks).
 */
@Controller
public class ViewController {

    private final StudentService studentService;

    public ViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Dashboard page — lists students, supports optional keyword search.
     * Also injects statistics into the model for the stat cards.
     *
     * @param search optional search keyword from the query param ?search=xxx
     * @param model  Spring's model object — used to pass data to the HTML template
     */
    @GetMapping("/")
    public String viewDashboard(@RequestParam(required = false) String search, Model model) {
        List<Student> students;

        // If a search keyword is provided, filter; otherwise show all
        if (search != null && !search.isBlank()) {
            students = studentService.searchStudents(search);
            model.addAttribute("search", search);
        } else {
            students = studentService.getAllStudents();
        }

        model.addAttribute("students", students);

        // Stats for the dashboard cards
        model.addAttribute("totalStudents", studentService.getTotalStudents());
        model.addAttribute("averageMarks", studentService.getAverageMarks());
        model.addAttribute("topStudent", studentService.getTopStudent());
        model.addAttribute("passingRate", studentService.getPassingRate());

        return "index";
    }

    /**
     * Shows the "Add Student" form with an empty Student object.
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("formTitle", "Add New Student");
        return "student-form";
    }

    /**
     * Shows the "Edit Student" form pre-populated with the student's current data.
     *
     * @param id the student ID from the URL path (e.g., /edit/5)
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("formTitle", "Edit Student");
        return "student-form";
    }

    /**
     * Handles form submission for both creating and updating a student.
     *
     * @param student          the student data bound from the form
     * @param bindingResult    holds any validation errors from @Valid
     * @param redirectAttrs    used to add flash messages that persist after redirect
     */
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute @Valid Student student,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttrs) {
        // If the form has validation errors, redisplay the form with error messages
        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", student.getId() == null ? "Add New Student" : "Edit Student");
            return "student-form";
        }

        try {
            if (student.getId() == null) {
                studentService.addStudents(student);
                redirectAttrs.addFlashAttribute("successMessage", "Student added successfully! 🎉");
            } else {
                studentService.updateStudent(student.getId(), student);
                redirectAttrs.addFlashAttribute("successMessage", "Student updated successfully! ✅");
            }
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Could not save student: " + e.getMessage());
        }

        return "redirect:/";
    }

    /**
     * Deletes a student by ID and redirects back to the dashboard.
     */
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            studentService.deleteStudent(id);
            redirectAttrs.addFlashAttribute("successMessage", "Student record deleted. 🗑️");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Could not delete student: " + e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * Renders the custom login page.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
