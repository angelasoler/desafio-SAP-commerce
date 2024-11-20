package com.example.online_courses.controller;

import com.example.online_courses.dto.CourseDTO;
import com.example.online_courses.model.Course;
import com.example.online_courses.populator.EnrollmentPopulator;
import com.example.online_courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String redirectToDocs() {
        return "redirect:/api/docs";
    }

    @GetMapping("/api/docs")
    public String apiDocs() {
        return """
            Welcome to the Online Courses API! Here are the available endpoints:

            **Student Management:**
            - POST   /students                    : Register a new student. (Body: Student)
            
            **Course Management:**
            - POST   /courses                     : Register a new course. (Body: Course)
            
            **Enrollment Management:**
            - POST   /enrollments                 : Enroll a student in a course. (Params: studentId, courseId)
            - GET    /enrollments/coursesbyStudent/{studentId} : List all courses for a specific student.
            - GET    /enrollments/studentsbyCourse/{courseId}  : List all students for a specific course.
            """;
    }
}
