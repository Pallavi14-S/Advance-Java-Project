package com.cdac.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.CategoryDTO;
import com.cdac.service.CatogoryService;



@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CatogoryService catogoryService;
	
	@PostMapping("/multiple/courses/{courseId}")
	public ResponseEntity<?> addMultipleCategoriesToCourses(@PathVariable Long courseId,
			@RequestBody Set<CategoryDTO> dto)
	{
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(catogoryService.addCategory(courseId,dto));
					
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
		
	}
	

}
