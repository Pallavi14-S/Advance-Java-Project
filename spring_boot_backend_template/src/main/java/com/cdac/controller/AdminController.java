package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.CourseRespDTO;
import com.cdac.dto.StudentResponseDTO;
import com.cdac.dto.UserRespDTO;
import com.cdac.dto.UserSignUpReqDTO;
import com.cdac.entity.Course;
import com.cdac.service.AdminService;
import com.cdac.service.CourseService;
import com.cdac.service.StudentService;
import com.cdac.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController("/admin")
@AllArgsConstructor
@Validated
public class AdminController {
	

	private final CourseService courseService;
	private UserService userService; 
	private StudentService studentService;
	
	@GetMapping("/allCourse")
	@Operation(description="Get All Courses")
	public ResponseEntity<?> getAvailableCourses(){
		System.out.println("in get available courses");
		List<CourseRespDTO> courses =  courseService.findAllAvailableCourses();
		if(courses.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(courses);
		
		
		
		
	}
	
	@PostMapping("/addstaff")
	@Operation(description = "Add Staff Details")
	
	public ResponseEntity<?> userSignUp(@RequestBody @Valid UserSignUpReqDTO dto)
	{
		System.out.println("in user sign up controller..." + dto);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.registerNewUser(dto));
	}
	
	
	@GetMapping("/getStaff/{staffId}")
	@Operation(description="Get Staff Details By ID")
	public ResponseEntity<?> getStaffDetails(@PathVariable Long staffId)
	{
		UserRespDTO staff=userService.getStaffDetails(staffId);
		return ResponseEntity.ok(staff);
		
	}
	
	@GetMapping("/getenrollStudent/{courseId}")
	public ResponseEntity<?> getEnrollStudent(@PathVariable Long courseId){
		
		List<StudentResponseDTO> studentList=studentService.getEnrollStudent(courseId);
		return ResponseEntity.ok(studentList);
	}
	
	@DeleteMapping("/deletStaff/{staffId}")
	public ResponseEntity<?> deleteStaff(@PathVariable Long staffId)
	{
		
		 return ResponseEntity.ok(
				 userService.deleteDetails(staffId));
		
	}
	
	@DeleteMapping("/deleteStudent/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long studentId)
	{
		return ResponseEntity.ok(
				userService.deleteStudent(studentId));
	}
	

}
