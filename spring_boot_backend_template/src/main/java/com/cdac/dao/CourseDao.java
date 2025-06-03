package com.cdac.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.cdac.dto.CourseRespDTO;
import com.cdac.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long>{

	public List<Course> findByStatusTrue();
	//boolean existByName(String courseName);

}
