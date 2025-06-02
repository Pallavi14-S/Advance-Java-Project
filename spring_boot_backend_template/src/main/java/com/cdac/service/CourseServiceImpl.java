package com.cdac.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dao.CourseDao;
import com.cdac.dto.CourseRespDTO;

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

}
