package com.kosta.finalProject.services;

import java.io.Console;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.login.SecurityUser;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.LoginRepository;
import com.kosta.finalProject.persistences.UserBodyRepository;
import com.kosta.finalProject.persistences.UserRepository;

import lombok.extern.java.Log;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	LoginRepository repo;
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserBodyRepository bodyrepo;
	


  	public UserVO selectById(String userId) {
  		return repository.findById(userId).get();
  	}

  	public void insertBMI(double bmi) {
  		UserBodyVO body = UserBodyVO.builder()
  				.userBmi(bmi).build();
  		bodyrepo.save(body);
  	}
  	
  	
  	public void updateBMI(UserBodyVO body) {
  		bodyrepo.save(body);
  	}

  	
  	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDetails user = repo.findById(userId)
				.filter(u->u!=null).map(u->new SecurityUser(u)).get();
		return user;
	}
  	
  	
}
