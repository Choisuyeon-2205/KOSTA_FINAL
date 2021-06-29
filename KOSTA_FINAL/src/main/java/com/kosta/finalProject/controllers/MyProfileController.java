package com.kosta.finalProject.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.finalProject.BMI.BMICalculator;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class MyProfileController {

	@Autowired
	UserService userservice;
	
	
	@GetMapping("/login/profile")
	public void profile() {
	       
	}
	    
	@PostMapping("/login/profile")
	public String profilePost(UserBodyVO body, Principal principal,Authentication authentication) {
	   UserVO user = new UserVO();
	   user.setUserId(principal.getName());
	body.setUser(user);
	   BMICalculator bmi = new BMICalculator();
	   body.setUserBmi(bmi.bmicalculator(body.getWeight(), body.getHeight()));
	   System.out.println("UserBodyVO : " + body);
	   userservice.updateBMI(body);
	   return "redirect:/main";
	}
	    
	@GetMapping("/login/myprofile")
	public void myprofile() {
	       
	}
	    
	@GetMapping("/body/bmi")
	public void bmiGraph() {
		
	}
}
