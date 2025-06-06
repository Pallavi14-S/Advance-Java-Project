package com.cdac.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of="categoryName",callSuper=false)
public class CategoryDTO extends BaseDto{

	private String categoryName;
	private String categoryDescription;
	private double price;
	private String content;
}
