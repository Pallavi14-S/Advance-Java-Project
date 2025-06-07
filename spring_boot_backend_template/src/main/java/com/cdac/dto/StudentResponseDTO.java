package com.cdac.dto;

import java.util.Set;

import com.cdac.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO extends BaseDto {
    private String name;
    private String email;
    private String phone;
    private UserRole role;
    private Set<CourseRespDTO> enrolledCourses;
} 
