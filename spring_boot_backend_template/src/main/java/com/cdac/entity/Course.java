package com.cdac.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="courses")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude="categories")
public class Course extends BaseEntity {
	@Column(length = 100, unique = true)
	private String name;
	private String description;
	private boolean status;

	@OneToMany(mappedBy = "myCourse",cascade=CascadeType.ALL,
			orphanRemoval=true,fetch=FetchType.EAGER
			)
	private List<Category> categories=new ArrayList<>(); 
	public Course(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	

}
