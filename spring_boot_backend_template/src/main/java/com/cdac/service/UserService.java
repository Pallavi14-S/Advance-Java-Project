package com.cdac.service;

import com.cdac.dto.UserRespDTO;
import com.cdac.dto.UserSignInReqDTO;
import com.cdac.dto.UserSignUpReqDTO;

public interface UserService {
	
  UserRespDTO registerNewUser(UserSignUpReqDTO dto);
  
  UserRespDTO authenticateUser(UserSignInReqDTO dto);

}
