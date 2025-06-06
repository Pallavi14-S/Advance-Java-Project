package com.cdac.dto;

import org.hibernate.validator.constraints.Length;

import com.cdac.entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserSignUpReqDTO {

	@NotBlank(message = "Please fill First name")
	private String firstName;
	
	@NotBlank
	@Length(min = 5 , max = 20 , message ="Please fill Last Name")
	private String lastName;
	
	@Email(message = "Invalid email format")
	private String email;
	
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*]).{5,20})", message = "invalid password format!!!!")
	private String password;
	
	@NotNull(message = "Role can't be empty..")
	private UserRole role;
	
	
}
