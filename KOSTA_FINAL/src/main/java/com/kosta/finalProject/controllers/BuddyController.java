package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.BuddyService;
import com.kosta.finalProject.services.UserBodyService;
import com.kosta.finalProject.services.UserService;

@Controller
public class BuddyController {
	@Autowired
	UserService uservice;
	@Autowired
	UserBodyService ubservice;
	
	// 버디 없을 때 main
	@GetMapping("/buddy/newbuddy")
	public void getNewBuddy(Model model) {
		
	}
	
	@RequestMapping("/buddy/searchbuddy")
	public String searchNewBuddy(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
		//model.addAttribute("userid",user.getUserId());
		
		UserBodyVO mybody= ubservice.selectByUser(user.getUserId());
		List<UserBodyVO> buddys= ubservice.findIsBuddy(user.getUserId(), mybody.getBmiGroup());
		if(buddys.size()==0) {
			return "/buddy/buddyfail";
		}
		
		return "/buddy/buddyprofile";	
	}

}
