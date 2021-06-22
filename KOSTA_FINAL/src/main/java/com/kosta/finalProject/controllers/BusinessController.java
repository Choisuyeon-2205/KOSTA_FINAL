package com.kosta.finalProject.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.models.BusinessVO;


@Controller
public class BusinessController {

	@GetMapping("/business/businessMain")
	public void getMain(Model model) {
	}
	
	@GetMapping("/business/myPage")
	public void getMyPage(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		BusinessVO business = BusinessVO.builder()
				.businessId(userDetails.getUsername())				
				.build();
		model.addAttribute("business",business.getBusinessId());
	}
	
}
