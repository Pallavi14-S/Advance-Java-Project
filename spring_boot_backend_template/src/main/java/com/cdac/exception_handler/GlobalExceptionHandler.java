package com.cdac.exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdac.custom_exceptions.ApiException;
import com.cdac.custom_exceptions.AuthenticationException;
import com.cdac.custom_exceptions.ResourceNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice 

public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		System.out.println("in catch all");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiException(e.getMessage()));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in handle res not found exc");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(e.getMessage()));
	}
	
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<?> handleMethodArgumentNotValidException
		(MethodArgumentNotValidException e) {
			System.out.println("in handle method  arg invalid  exc : P.L validation failures");
			List<FieldError> rejectedFields = e.getFieldErrors();
			Map<String, String> errorMap = rejectedFields.stream() 
			.collect(
					Collectors.toMap
					(FieldError::getField,FieldError::getDefaultMessage));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(errorMap);
		}
		
		@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
			System.out.println("in handle constraint violation  exc");
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiException(e.getMessage()));
		}
		
		@ExceptionHandler(AuthenticationException.class)
		public ResponseEntity<?> handleAuthenticationException
		(AuthenticationException e) {
			System.out.println("in handle auth exc");
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiException(e.getMessage()));
		}
}
