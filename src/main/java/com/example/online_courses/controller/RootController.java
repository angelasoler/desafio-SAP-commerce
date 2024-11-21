package com.example.online_courses.controller;

import com.example.online_courses.dto.CourseDTO;
import com.example.online_courses.model.Course;
import com.example.online_courses.populator.EnrollmentPopulator;
import com.example.online_courses.repository.CourseRepository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RootController {

    @GetMapping("/")
    public RedirectView redirectToSwagger() {
        return new RedirectView("/api/docs");
    }

    @GetMapping("/api/docs")
    public Map<String, Object> apiDocs() {
        Map<String, Object> docs = new LinkedHashMap<>();
        
        docs.put("Welcome", "Welcome to the Online Courses API! Here are the available endpoints:");
        
        Map<String, String> studentManagement = new LinkedHashMap<>();
        studentManagement.put("POST /students", "Register a new student. (Body: Student)");

        Map<String, String> courseManagement = new LinkedHashMap<>();
        courseManagement.put("POST /courses", "Register a new course. (Body: Course)");

        Map<String, String> enrollmentManagement = new LinkedHashMap<>();
        enrollmentManagement.put("POST /enrollments", "Enroll a student in a course. (Params: studentId, courseId)");
        enrollmentManagement.put("GET /enrollments/coursesbyStudent/{studentId}", "List all courses for a specific student.");
        enrollmentManagement.put("GET /enrollments/studentsbyCourse/{courseId}", "List all students for a specific course.");

        docs.put("Student Management", studentManagement);
        docs.put("Course Management", courseManagement);
        docs.put("Enrollment Management", enrollmentManagement);

        return docs;
    }
}
