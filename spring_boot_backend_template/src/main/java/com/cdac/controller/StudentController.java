package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.CourseRespDTO;
import com.cdac.dto.StudentLoginRequestDTO;
import com.cdac.dto.StudentRequestDTO;
import com.cdac.dto.StudentResponseDTO;
import com.cdac.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/login")
    @Operation(description = "Student login")
    public ResponseEntity<?> loginStudent(@Validated @RequestBody StudentLoginRequestDTO loginDTO) {
        StudentResponseDTO response = studentService.loginStudent(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(description = "Register a new student")
    public ResponseEntity<?> registerStudent(@Validated @RequestBody StudentRequestDTO studentDTO) {
        StudentResponseDTO response = studentService.registerStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{studentId}")
    @Operation(description = "Get student details by ID")
    public ResponseEntity<?> getStudentDetails(@PathVariable Long studentId) {
        StudentResponseDTO student = studentService.getStudentDetails(studentId);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{studentId}")
    @Operation(description = "Update student details")
    public ResponseEntity<?> updateStudentDetails(
            @PathVariable Long studentId,
            @Validated @RequestBody StudentRequestDTO studentDTO) {
        StudentResponseDTO response = studentService.updateStudentDetails(studentId, studentDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{studentId}/courses")
    @Operation(description = "Get all courses enrolled by the student")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Long studentId) {
        List<CourseRespDTO> courses = studentService.getEnrolledCourses(studentId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    @Operation(description = "Enroll student in a course")
    public ResponseEntity<?> enrollInCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        StudentResponseDTO response = studentService.enrollInCourse(studentId, courseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    @Operation(description = "Unenroll student from a course")
    public ResponseEntity<?> unenrollFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        StudentResponseDTO response = studentService.unenrollFromCourse(studentId, courseId);
        return ResponseEntity.ok(response);
    }
}

