package com.cdac.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.custom_exceptions.ApiException;
import com.cdac.dao.CourseDao;
import com.cdac.dto.CourseRespDTO;
import com.cdac.entity.Course;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseDao courseDao;
	private final ModelMapper modelMapper;
	
	@Override
	public List<CourseRespDTO> findAllAvailableCourses() {
		
		return courseDao.findByStatusTrue()
				.stream()
				.map(entity ->modelMapper.map(entity,CourseRespDTO.class))
				.toList();
		
	}

	@Override
	public CourseRespDTO addCourse(CourseRespDTO dto) {
		
		if(courseDao.existsByName(dto.getName()))
			throw new ApiException("Duplicate course name..!!");
		
		Course entity = modelMapper.map(dto, Course.class);
		
		entity.setStatus(true);
		
		Course persistentEntity = courseDao.save(entity);
		
		return modelMapper.map(persistentEntity, CourseRespDTO.class);
	}

}
