package com.kosta.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public UserVO selectById(String userId) {
		return repository.findById(userId).get();
	}
}
