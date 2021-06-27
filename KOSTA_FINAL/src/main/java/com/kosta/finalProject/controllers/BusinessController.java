package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.finalProject.models.AreasVO;
import com.kosta.finalProject.models.BusinessVO;
import com.kosta.finalProject.models.CenterReviewVO;
import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.services.AreasService;
import com.kosta.finalProject.services.BusinessService;
import com.kosta.finalProject.services.CenterReviewService;
import com.kosta.finalProject.services.CenterService;
import com.kosta.finalProject.services.CurriculumService;
import com.kosta.finalProject.services.ExerciseTypeService;
import com.kosta.finalProject.services.TrainerService;


@Controller
public class BusinessController {
	@Autowired
	BusinessService bservice;
	@Autowired
	CenterService cservice;
	@Autowired
	TrainerService tservice;
	@Autowired
	CurriculumService curservice;
	@Autowired
	CenterReviewService crservice;
	@Autowired
	AreasService areaService;
	@Autowired
	ExerciseTypeService etservice;
	

	@GetMapping("/business/businessMain")
	public void getMain(Model model) {
	}
	
	@GetMapping("/business/myPage")
	public void getMyPage(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		BusinessVO business = bservice.selectById(userDetails.getUsername());
		model.addAttribute("business",business);
	}
	
	@GetMapping("/business/myCenters")
	public void getMyCenters(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		BusinessVO business = BusinessVO.builder()
				.businessId(userDetails.getUsername())
				.build();
		model.addAttribute("business",business);
		
		List<CenterVO> centerlist= cservice.selectByBusiness(business);
		model.addAttribute("centerlist",centerlist);
		
		String[] area1s= areaService.selectAllArea1();
		List<AreasVO> arealist= areaService.selectAll();
		model.addAttribute("area1s",area1s);
		model.addAttribute("arealist",arealist);
	}
	
	@GetMapping("/business/centerdetail")
	public void selectById(Model model, int cnum, Authentication authentication) {
		CenterVO center= cservice.selectById(cnum);
		model.addAttribute("center",center);
		
		List<CurriculumVO> curlist= curservice.selectByCenter(cnum);
		model.addAttribute("curlist",curlist);
		
		List<CenterReviewVO> revlist= crservice.selectByCenter(cnum);
		model.addAttribute("revlist",revlist);
		
		List<ExerciseTypeVO> etypelist= etservice.selectAll();
		model.addAttribute("etypelist",etypelist);
		
		List<TrainerVO> trainerlist= tservice.selectByCenter(cnum);
		model.addAttribute("trainerlist",trainerlist);
		
		double gprefer;
		List<CenterReviewVO> reviews= crservice.selectByCenter(cnum);
		if(reviews.isEmpty()) {
			gprefer= 0.0;
		}else {
			gprefer= crservice.calAvgPreference(cnum);
		}
			
		model.addAttribute("gprefer",gprefer);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		BusinessVO business = BusinessVO.builder()
				.businessId(userDetails.getUsername())
				.build();
		model.addAttribute("business",business);
		
		String[] area1s= areaService.selectAllArea1();
		List<AreasVO> arealist= areaService.selectAll();
		model.addAttribute("area1s",area1s);
		model.addAttribute("arealist",arealist);
	}
	
}
