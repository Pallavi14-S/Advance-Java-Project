package com.cdac.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Student extends BaseEntity {
    
    @Column(length = 100)
    private String name;
    
    @Column(unique = true, length = 100)
    private String email;
    
    @Column(length = 10)
    private String phone;
    
    @Column(length = 20, nullable = false)
    private String password;
    
    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> enrolledCourses = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;
    
    // Constructor with required fields
    public Student(String name, String email, String phone, String password) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = UserRole.STUDENT;
    }
    
    // Helper method to add a course
    public void addCourse(Course course) {
        enrolledCourses.add(course);
    }
    
    // Helper method to remove a course
    public void removeCourse(Course course) {
        enrolledCourses.remove(course);
    }

	public Student(String name, String email, String phone, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}
} 
