package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.models.CenterReviewVO;
import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.CenterReviewService;
import com.kosta.finalProject.services.CenterService;
import com.kosta.finalProject.services.CurriculumRegisterService;
import com.kosta.finalProject.services.CurriculumService;
import com.kosta.finalProject.services.ExerciseTypeService;
import com.kosta.finalProject.services.TrainerService;

@Controller
public class CenterController {

	@Autowired
	CenterService centerservice;
	@Autowired
	TrainerService tservice;
	@Autowired
	ExerciseTypeService etservice;
	@Autowired
	CurriculumService curservice;
	@Autowired
	CenterReviewService crservice;
	@Autowired
	CurriculumRegisterService curRegService;
	
	@GetMapping("/center/centerlist")
	public void selectAll(Model model) {
		List<CenterVO> centerlist= centerservice.selectAll();	
		model.addAttribute("centerlist",centerlist);

	}
	
	@GetMapping("/center/centerdetail")
	public void selectById(Model model, int cnum, Authentication authentication) {
		CenterVO center= centerservice.selectById(cnum);
		model.addAttribute("center",center);
		
		List<TrainerVO> trainerlist= tservice.selectByCenter(cnum);
		model.addAttribute("trainerlist",trainerlist);
		
		List<ExerciseTypeVO> etypelist= etservice.selectAll();
		model.addAttribute("etypelist", etypelist);
		
		List<CurriculumVO> curlist= curservice.selectByCenter(cnum);
		model.addAttribute("curlist",curlist);
		
		double gprefer;
		List<CenterReviewVO> reviews= crservice.selectByCenter(cnum);
		if(reviews.isEmpty()) {
			gprefer= 0.0;
		}else {
			gprefer= crservice.calAvgPreference(cnum);
		}
			
		model.addAttribute("gprefer",gprefer);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		UserVO user = UserVO.builder()
				.userId(userDetails.getUsername())				
				.build();
		model.addAttribute("userid",user.getUserId());
		List<CurriculumVO> userCurlist= curRegService.selectByCurRegId(cnum, user.getUserId());
		model.addAttribute("userCurlist",userCurlist);
	}
}
