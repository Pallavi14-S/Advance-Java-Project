package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserSignInReqDTO {

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
}
