
package com.example.online_courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.online_courses.model.Course;
import com.example.online_courses.model.Enrollment;
import com.example.online_courses.model.Student;

import com.example.online_courses.repository.StudentRepository;
import com.example.online_courses.repository.CourseRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class OnlineCoursesApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void courseControllerBeanExists() {
        assertThat(context.containsBean("courseController")).isTrue();
    }

    @Test
    void studentControllerBeanExists() {
        assertThat(context.containsBean("studentController")).isTrue();
    }

    @Test
    void enrollmentControllerBeanExists() {
        assertThat(context.containsBean("enrollmentController")).isTrue();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegistrarNovoEstudante() throws Exception {
        Student student = new Student();
        student.setName("Ana Silva");
        student.setEmail("ana.silva@example.com");

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Ana Silva"))
                .andExpect(jsonPath("$.email").value("ana.silva@example.com"));
    }

    @Test
    void testRegistrarNovoCurso() throws Exception {
        Course course = new Course();
        course.setDescription("Desenvolvimento Web com Spring Boot");

        mockMvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Desenvolvimento Web com Spring Boot"));
    }

    void testInscreverEstudanteEmCurso() throws Exception {
        Student student = new Student();
        student.setName("Ana Silva");
        student.setEmail("ana.silva@example.com");

        Course course = new Course();
        course.setDescription("Desenvolvimento Web com Spring Boot");

        Enrollment enrollment = new Enrollment();

        mockMvc.perform(post("/enrollments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enrollment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.studentId").value(student.getId()))
                .andExpect(jsonPath("$.courseId").value(course.getId()));
    }

    @Test
    void testListarCursosPorEstudante() throws Exception {
        Long studentId = 1L;

        mockMvc.perform(get("/enrollments/coursesbyStudent/{studentId}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
            
    }

    @Test
    void testListarEstudantesPorCurso() throws Exception {
        Long courseId = 1L;

        mockMvc.perform(get("/enrollments/studentsbyCourse/{courseId}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
            
    }
}
