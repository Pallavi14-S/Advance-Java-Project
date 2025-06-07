package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.CourseRespDTO;
import com.cdac.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor

public class CourseController {
	
	private final CourseService courseService;

	
	@GetMapping
	@Operation(description = "Get All Avaliable Courses")
	
	public ResponseEntity<?> getAllAvaliableCourses()
	{
		List<CourseRespDTO> courses = courseService.findAllAvailableCourses();
		if(courses.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(courses);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewCourse(@RequestBody CourseRespDTO dto)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(courseService.addCourse(dto));
	}
}
