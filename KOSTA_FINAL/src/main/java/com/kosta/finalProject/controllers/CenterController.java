package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kosta.finalProject.models.AreasVO;
import com.kosta.finalProject.models.BusinessVO;
import com.kosta.finalProject.models.CenterReviewVO;
import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.AreasService;
import com.kosta.finalProject.services.CenterReviewService;
import com.kosta.finalProject.services.CenterService;
import com.kosta.finalProject.services.CurriculumRegisterService;
import com.kosta.finalProject.services.CurriculumService;
import com.kosta.finalProject.services.ExerciseTypeService;
import com.kosta.finalProject.services.TrainerService;
import com.kosta.finalProject.services.UserService;

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
	@Autowired
	AreasService areaService;
	@Autowired
	UserService uservice;
	
	@GetMapping("/center/centerlist")
	public void selectAll(Model model) {
		List<CenterVO> centerlist= centerservice.selectAll();	
		model.addAttribute("centerlist",centerlist);
		
		String[] area1s= areaService.selectAllArea1();
		List<AreasVO> arealist= areaService.selectAll();
		model.addAttribute("area1s",area1s);
		model.addAttribute("arealist",arealist);
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
		UserVO user = uservice.selectById(userDetails.getUsername());
		model.addAttribute("userid",user.getUserId());
		List<CurriculumVO> userCurlist= curRegService.selectByCurRegId(cnum, user.getUserId());
		model.addAttribute("userCurlist",userCurlist);
		System.out.println(userCurlist);
	}
	
	@PutMapping("/center/updateCenter/{cnum}")
	public ResponseEntity<CenterVO> updateCenter(@PathVariable int cnum,
			@RequestBody CenterVO center, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		BusinessVO business = BusinessVO.builder()
				.businessId(userDetails.getUsername())
				.build();
		center.setBusiness(business);
		center.setCenterNum(cnum);
		
		centerservice.updateCenter(center);
		
		return new ResponseEntity<>(center, HttpStatus.OK);
	}
	
	@PostMapping("/center/insertCenter")
	public ResponseEntity<CenterVO> insertCenter(@RequestBody CenterVO center, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		BusinessVO business = BusinessVO.builder()
				.businessId(userDetails.getUsername())
				.build();
		center.setBusiness(business);
		center.setCenterPreferance(0l);
		centerservice.insertCenter(center);
		
		return new ResponseEntity<>(center, HttpStatus.OK);
	}
	
	@DeleteMapping("/center/deleteCenter/{cnum}")
	public  ResponseEntity<String> deleteCenter(@PathVariable int cnum) {
		int result= centerservice.deleteCenter(cnum);

		return result==1? new ResponseEntity<>("삭제완료", HttpStatus.OK):new ResponseEntity<>("삭제실패", HttpStatus.OK);
	}
}
