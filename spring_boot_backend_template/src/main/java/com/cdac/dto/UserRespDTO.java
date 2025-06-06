package com.cdac.dto;

import com.cdac.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRespDTO extends BaseDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
		
	private UserRole role;

}
