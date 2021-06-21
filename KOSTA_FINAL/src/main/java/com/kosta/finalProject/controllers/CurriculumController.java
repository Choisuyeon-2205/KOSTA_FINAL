package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.CurriculumRegisterService;
import com.kosta.finalProject.services.CurriculumService;

@Controller
public class CurriculumController {

	@Autowired
	CurriculumService curservice;
	@Autowired
	CurriculumRegisterService curregservice;
	
	@GetMapping("/center/myCurriculum")
	public void selectMyCurriculum(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		UserVO user = UserVO.builder()
				.userId(userDetails.getUsername())				
				.build();
		
		List<CurriculumVO> mycurlist= curregservice.selectAllByCurRegId(user.getUserId());
		model.addAttribute("mycurlist",mycurlist);
	}
}
