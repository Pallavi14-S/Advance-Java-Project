package com.cdac.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.dto.CategoryDTO;
import com.cdac.entity.Category;


public interface CategoryDao extends JpaRepository<Category, Long>{

	boolean existsByCategoryNameAndMyCourseId(String categoryName,Long id);


}
