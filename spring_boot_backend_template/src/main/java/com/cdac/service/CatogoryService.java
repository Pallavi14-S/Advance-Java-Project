package com.cdac.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.CategoryDTO;


public interface CatogoryService {

	ApiResponse addCategory(Long courseId, Set<CategoryDTO> dto);

}
