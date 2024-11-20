package com.example.online_courses.controller;

import com.example.online_courses.dto.StudentDTO;
import com.example.online_courses.model.Student;
import com.example.online_courses.populator.EnrollmentPopulator;
import com.example.online_courses.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public StudentDTO createStudent(@RequestBody Student student) {
        student.setRegisterDate(new Date());
        Student savedStudent = studentRepository.save(student);
        return EnrollmentPopulator.toStudentDTO(savedStudent);
    }
}
