package com.example.online_courses.dto;

import java.util.Date;
import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private Date registerDate;
}
