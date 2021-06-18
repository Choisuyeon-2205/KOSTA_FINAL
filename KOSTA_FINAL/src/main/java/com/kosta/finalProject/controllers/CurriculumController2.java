package com.kosta.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.CurriculumRegisterService;
import com.kosta.finalProject.services.CurriculumService;

@RestController
public class CurriculumController2 {
	@Autowired
	CurriculumService curservice;
	@Autowired
	CurriculumRegisterService curregservice;

	@PostMapping("/center/registerCurriculum/{cnum}")
	public String registerCurriculum(@PathVariable("cnum") int curnum, RedirectAttributes rttr, Authentication authentication) {
		System.out.println(curnum);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		UserVO user = UserVO.builder()
				.userId(userDetails.getUsername())				
				.build();	
		CurriculumRegisterVOId crid= new CurriculumRegisterVOId();
		CurriculumVO curriculum= curservice.selectById(curnum);
		crid.setCurriculum(curriculum);
		crid.setUser(user);
		
		CurriculumRegisterVO crvo= curregservice.selectById(crid);
		if(crvo!=null) {
			return "이미 등록한 강좌입니다";
		}
		
		crvo= new CurriculumRegisterVO();
		crvo.setId(crid);
		
		
		CurriculumRegisterVO result= curregservice.insertCurriculumRegister(crvo);
		//rttr.addFlashAttribute("resultMessage", result==null?"등록실패":"등록성공");		
		if(result!=null) { //등록성공했을 때만 count
			curriculum.setCurriculumState(curriculum.getCurriculumState()+1);
			curservice.updateCurriculum(curriculum);
		}
		//return new ResponseEntity<>(curservice.selectAll(), HttpStatus.CREATED);
		return result==null?"등록실패":"등록성공";
	}
}
