package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.StudentDTO;
import com.cdac.dto.StudentResponseDTO;
import com.cdac.service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor

public class StaffController {
	
private StudentService studentService;

 @GetMapping("/{staffId}/students")
public ResponseEntity<List<StudentDTO>> getStudentsByStaff(@PathVariable Long staffId) {
    List<StudentDTO> students = studentService.getStudentsByStaffId(staffId);
    return ResponseEntity.ok(students);
}
	

}
