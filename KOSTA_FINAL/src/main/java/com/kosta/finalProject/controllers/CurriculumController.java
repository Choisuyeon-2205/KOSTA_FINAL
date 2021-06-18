package com.kosta.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;
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
	
	@GetMapping("/center/curriculumdetail")
	public void selectById(Model model, int crnum) {
		CurriculumVO curriculum= curservice.selectById(crnum);
		model.addAttribute("curriculum",curriculum);
	}
	
	/*
	@PostMapping("/center/registerCurriculum")
	public String registerCurriculum(@RequestAttribute("cnum") int curnum, RedirectAttributes rttr, Authentication authentication) {
		System.out.println(curnum);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		UserVO user = UserVO.builder()
				.userId(userDetails.getUsername())				
				.build();	
		CurriculumRegisterVOId crid= new CurriculumRegisterVOId();
		CurriculumVO curriculum= curservice.selectById(curnum);
		crid.setCurriculum(curriculum);
		crid.setUser(user);
		CurriculumRegisterVO crvo= new CurriculumRegisterVO();
		crvo.setId(crid);
		
		CurriculumRegisterVO result= curregservice.insertCurriculumRegister(crvo);
		rttr.addFlashAttribute("resultMessage", result==null?"등록실패":"등록성공");		
		return "redirect:/center/centerlist";
	}
	*/
}
