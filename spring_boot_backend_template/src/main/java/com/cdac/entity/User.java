package com.cdac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password" , "myAddress"} , callSuper = true)

public class User extends BaseEntity {

	@Column(name = "first_name" , length = 20)
	private String firstName;
	
	@Column(name = "last_name", length = 30)
	private String lastName;
	
	@Column(length = 30, unique = true)
	private String email;
	
	@Column(length = 20, nullable = false)
	private String password;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private UserRole role;
	
	public User(String firstName, String lastName, String email, String password, UserRole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
}
