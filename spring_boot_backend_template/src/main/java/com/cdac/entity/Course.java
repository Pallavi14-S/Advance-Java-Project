package com.cdac.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToMany(mappedBy = "enrolledCourses")
	private Set<Student> enrolledStudents = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	
	public Course(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public void addCategory(Category categories) {
		this.categories.add(categories);
		categories.setMyCourse(this);
		
	}
	public void removeCategory(Category categories)
	{
		this.categories.remove(categories);
		categories.setMyCourse(null);
	}
	

}
