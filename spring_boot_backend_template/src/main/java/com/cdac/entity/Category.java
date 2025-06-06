package com.cdac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="categories",
uniqueConstraints = @UniqueConstraint
(columnNames = {"category_name","course_id"}))
@ToString(callSuper=true, exclude="myCourse")
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity{
	

	@Column(name="category_name", length=100,unique=true)
	private String categoryName;
	@Column(name="category_description" ,length=200)
	private String categoryDescription;
	private double price;
	private String content;
	@Column(name="is_available")
	private boolean status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course myCourse;
	public Category(String categoryDescription, String categoryName, double price, String content) {
		super();
		this.categoryDescription = categoryDescription;
		this.categoryName = categoryName;
		this.price = price;
		this.content = content;
	}
	
	
	
	

}
