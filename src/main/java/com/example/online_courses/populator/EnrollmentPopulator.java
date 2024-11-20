package com.example.online_courses.populator;

import com.example.online_courses.dto.StudentDTO;
import com.example.online_courses.dto.CourseDTO;
import com.example.online_courses.model.Student;
import com.example.online_courses.model.Course;

public class EnrollmentPopulator {

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRegisterDate(student.getRegisterDate());
        return dto;
    }

    public static CourseDTO toCourseDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setCreationDate(course.getCreationDate());
        return dto;
    }
}
