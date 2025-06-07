package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.UserSignInReqDTO;
import com.cdac.dto.UserSignUpReqDTO;
import com.cdac.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class UserController {
	
	private final UserService userService;
	

	
	@PostMapping("/signin")
	@Operation(description = "User Sign in")
	public ResponseEntity<?> userSignin(@RequestBody @Valid UserSignInReqDTO dto)
	{
		System.out.println("in user sign in controller..." + dto);

		return ResponseEntity.ok(userService.authenticateUser(dto));
	}
}
