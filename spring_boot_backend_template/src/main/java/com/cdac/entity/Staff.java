package com.cdac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "staff")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "password" , callSuper = true)

public class Staff extends BaseEntity {
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 30, unique = true)
	private String email;
	
	@Column(length = 10 , unique = true)
	private String phone;
	
	@Column(length = 20, nullable = false)
	private String password;

	public Staff(String name, String email, String phone, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	
}
