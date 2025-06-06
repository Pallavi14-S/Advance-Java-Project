package com.cdac.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ApiException;
import com.cdac.custom_exceptions.AuthenticationException;
import com.cdac.dao.UserDao;
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

}
