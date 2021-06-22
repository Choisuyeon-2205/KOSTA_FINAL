package com.kosta.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.models.UserVO;

@Controller
public class BuddyController {
	
	// 버디 신청주소.
	@GetMapping("/apply")
	public String apply(Model model, UserVO user) {
		
		
		
		return "";
	}

}
