package com.cdac.service;

import java.util.List;

import com.cdac.dto.CourseRespDTO;
import com.cdac.entity.Course;

public interface CourseService {

	

	List<CourseRespDTO> findAllAvailableCourses();

}
