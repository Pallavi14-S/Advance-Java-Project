package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.CourseRespDTO;
import com.cdac.entity.Course;
import com.cdac.service.AdminService;
import com.cdac.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController("/admin")
@AllArgsConstructor
@Validated
public class AdminController {
	

	private final CourseService courseService;
	
	@GetMapping
	@Operation(description="Get All Courses")
	public ResponseEntity<?> getAvailableCourses(){
		System.out.println("in get available courses");
		List<CourseRespDTO> courses =  courseService.findAllAvailableCourses();
		if(courses.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(courses);
		
		
		
		
	}
	

}
