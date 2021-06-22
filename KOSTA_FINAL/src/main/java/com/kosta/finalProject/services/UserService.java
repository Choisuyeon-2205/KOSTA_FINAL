package com.kosta.finalProject.services;

import java.security.Principal; 
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.login.SecurityUser;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.UserBodyRepository;
import com.kosta.finalProject.persistences.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	UserBodyRepository bodyrepo;
	


  	public UserVO selectById(String userId) {
  		return repository.findById(userId).get();
  	}
	


}
