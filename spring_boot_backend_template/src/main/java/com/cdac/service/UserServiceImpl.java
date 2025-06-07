package com.cdac.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ApiException;
import com.cdac.custom_exceptions.AuthenticationException;
import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.StudentDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.ApiResponse;
import com.cdac.dto.StudentResponseDTO;
import com.cdac.dto.UserRespDTO;
import com.cdac.dto.UserSignInReqDTO;
import com.cdac.dto.UserSignUpReqDTO;
import com.cdac.entity.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor

public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final ModelMapper modelMapper;
	private final StudentDao studentDao;
	
	@Override
	public UserRespDTO registerNewUser(UserSignUpReqDTO dto) {
		if(userDao.existsByEmail(dto.getEmail()))
			
		throw new ApiException("Duplicate email added..");
		
		User entity = modelMapper.map(dto, User.class);
		
		return modelMapper.map(userDao.save(entity), UserRespDTO.class);
	}

	@Override
	public UserRespDTO authenticateUser(UserSignInReqDTO dto) {

		User entity = userDao.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid email or password..!!"));
		return modelMapper.map(entity,UserRespDTO.class);
	}

	@Override
	public UserRespDTO getStaffDetails(Long staffId) {
	    User staff = userDao.findById(staffId)
	        .orElseThrow(() -> new ResourceNotFoundException("Invalid ID - Staff not found!"));

	    return modelMapper.map(staff, UserRespDTO.class);
	}
	@Override

	
	public ApiResponse deleteDetails(Long staffId) {
		if (userDao.existsById(staffId)) {
			userDao.deleteById(staffId);
			return new ApiResponse("Deleted Staff Details ");
		}
		return new ApiResponse("Invalid ID");
	}
	
	public ApiResponse deleteStudent(Long studentId) {
		
		System.out.println("Trying to delete student with ID: " + studentId);
		if (studentDao.existsById(studentId)) {
			studentDao.deleteById(studentId);
			return new ApiResponse("Deleted STudent Details ");
		}
		return new ApiResponse("Invalid Id ");
	}

}
