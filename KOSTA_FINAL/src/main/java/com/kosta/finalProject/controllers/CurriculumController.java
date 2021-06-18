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
	
}
