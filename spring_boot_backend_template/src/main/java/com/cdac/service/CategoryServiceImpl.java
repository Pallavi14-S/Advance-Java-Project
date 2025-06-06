package com.cdac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.cdac.custom_exception.ResourceNotFoundException;
import com.cdac.dao.CategoryDao;
import com.cdac.dao.CourseDao;
import com.cdac.dto.ApiResponse;
import com.cdac.dto.CategoryDTO;
import com.cdac.entity.Category;
import com.cdac.entity.Course;

@Service
@Transactional
public class CategoryServiceImpl implements CatogoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private ModelMapper modelMapper;
	
	public ApiResponse addCategory(Long courseId, Set<CategoryDTO> dtos) {
		List<String>dupCategoryNames=new ArrayList<>();
		Course courseEntity=courseDao.findById(courseId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Course ID"));
	
	dtos.stream().filter(dto->{
		boolean exists=categoryDao.existsByCategoryNameAndMyCourseId(dto.getCategoryName(),courseId);
	if(exists)
		dupCategoryNames.add(dto.getCategoryName());
	return !exists;
	}).forEach(dto->{
		Category entity = modelMapper.map(dto,Category.class);
		entity.setStatus(true);
		courseEntity.addCategory(entity);
	});
		
	if(dupCategoryNames.isEmpty())
		return new ApiResponse("Added all new non duplicate course categories");
	
		return new ApiResponse("These categories with duplicates names are not added - " +dupCategoryNames);
	}

}
