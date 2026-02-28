package com.student.Management.System.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler — catches exceptions from any @Controller and
 * returns a friendly error page instead of a raw Spring error.
 *
 * @ControllerAdvice means this class applies to all controllers in the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the case where a student record is not found.
     */
    @ExceptionHandler(StudentNotFoundException.class)
    public String handleStudentNotFound(StudentNotFoundException ex, Model model) {
        model.addAttribute("errorTitle", "Student Not Found");
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * Catches any other unexpected exception and shows a generic error page.
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("errorTitle", "Something Went Wrong");
        model.addAttribute("errorMessage",
                "An unexpected error occurred. Please try again or contact support.");
        return "error";
    }
}
