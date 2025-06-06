package com.cdac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

public class Course extends BaseEntity {
	
	@Column(length = 100, unique = true)
	private String name;
	
	private String description;
	
	private boolean status;

	public Course(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
}
