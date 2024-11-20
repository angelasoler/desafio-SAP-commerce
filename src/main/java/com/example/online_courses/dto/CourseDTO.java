package com.example.online_courses.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
}
