package com.example.online_courses.controller;

import com.example.online_courses.dto.CourseDTO;
import com.example.online_courses.model.Course;
import com.example.online_courses.populator.EnrollmentPopulator;
import com.example.online_courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public CourseDTO createCourse(@RequestBody Course course) {
        course.setCreationDate(new Date());
        Course savedCourse = courseRepository.save(course);
        return EnrollmentPopulator.toCourseDTO(savedCourse);
    }
}
