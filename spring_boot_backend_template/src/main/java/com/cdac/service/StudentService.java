package com.cdac.service;

import java.util.List;

import com.cdac.dto.CourseRespDTO;
import com.cdac.dto.StudentLoginRequestDTO;
import com.cdac.dto.StudentRequestDTO;
import com.cdac.dto.StudentResponseDTO;

public interface StudentService {
    StudentResponseDTO registerStudent(StudentRequestDTO studentDTO);
    StudentResponseDTO getStudentDetails(Long studentId);
    StudentResponseDTO updateStudentDetails(Long studentId, StudentRequestDTO studentDTO);
    List<CourseRespDTO> getEnrolledCourses(Long studentId);
    StudentResponseDTO enrollInCourse(Long studentId, Long courseId);
    StudentResponseDTO unenrollFromCourse(Long studentId, Long courseId);
    List<StudentResponseDTO> getEnrollStudent(Long courseId);
    StudentResponseDTO loginStudent(StudentLoginRequestDTO loginDTO);
} 
