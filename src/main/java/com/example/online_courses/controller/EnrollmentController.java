package com.example.online_courses.controller;

import com.example.online_courses.dto.StudentDTO;
import com.example.online_courses.dto.CourseDTO;
import com.example.online_courses.model.Student;
import com.example.online_courses.model.Course;
import com.example.online_courses.model.Enrollment;
import com.example.online_courses.populator.EnrollmentPopulator;
import com.example.online_courses.repository.StudentRepository;
import com.example.online_courses.repository.CourseRepository;
import com.example.online_courses.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public String registerStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student == null || course == null) {
            return "Student or Course not found.";
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setRegisterDate(new Date());

        enrollmentRepository.save(enrollment);

        return "Student successfully enrolled";
    }

    @GetMapping("/coursesbyStudent/{studentId}")
    public List<CourseDTO> listCoursesByStudent(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map(enrollment -> EnrollmentPopulator.toCourseDTO(enrollment.getCourse()))
                .collect(Collectors.toList());
    }

    @GetMapping("/studentsbyCourse/{courseId}")
    public List<StudentDTO> listCoursesByCourse(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        return enrollments.stream()
                .map(enrollment -> EnrollmentPopulator.toStudentDTO(enrollment.getStudent()))
                .collect(Collectors.toList());
    }
}
