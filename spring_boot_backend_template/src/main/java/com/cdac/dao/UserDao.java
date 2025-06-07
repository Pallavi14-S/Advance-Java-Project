package com.cdac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	boolean existsByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String em , String pass);
	

}
