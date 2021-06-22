package com.kosta.finalProject.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.DietDiaryReplyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.DietDiaryBoardService;
import com.kosta.finalProject.services.DietDiaryReplyService;
import com.kosta.finalProject.services.UserService;

import lombok.extern.java.Log;
@Log
@RestController
@RequestMapping("/replies/*")
public class DietDiaryBoardReplyController {

	
	
	@Autowired
	DietDiaryReplyService service;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	DietDiaryBoardService dietservice;
	
	//특정 board에 모든댓글 조회
	@GetMapping("/dboard/{diaryNum}")
	public ResponseEntity<List<DietDiaryReplyVO>> selectAll(@PathVariable int diaryNum) {
		DietDiaryBoardVO dboard = DietDiaryBoardVO.builder().diaryNum(diaryNum).build();
		
		System.out.println("controller");
		return new ResponseEntity<>(service.selectAll(dboard), HttpStatus.OK);
	}
	
	
	
	//특정댓글 조회
	@GetMapping("/{diaryRplNum}")
	public ResponseEntity<DietDiaryReplyVO> viewReply(@PathVariable Integer diaryRplNum){
		return new ResponseEntity<>(service.selectById(diaryRplNum),HttpStatus.OK);
	}

	
	
	
	//특정보드 댓글 입력 후 재조회
	@PostMapping("/{diaryNum}")
	public ResponseEntity<List<DietDiaryReplyVO>> addReply(@PathVariable Integer diaryNum,
			Authentication authentication,
			@RequestBody DietDiaryReplyVO dreply) {
		
		log.info("addReply-------------------------------------------------------");
		log.info("diaryNum" + diaryNum);
		log.info("dreply" + dreply);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user =  uservice.selectById(userDetails.getUsername());
		
		DietDiaryBoardVO dboard = dietservice.selectById(diaryNum);
		dreply.setDboard(dboard);
		dreply.setUser(user);
		service.updateOrInsert(dreply);
		return new ResponseEntity<>(service.selectAll(dboard), HttpStatus.CREATED);
	}
	
	
	//댓글 삭제
	@DeleteMapping("/{diaryNum}/{diaryRplNum}")
	public ResponseEntity<List<DietDiaryReplyVO>> deleteByRplno(@PathVariable Integer diaryRplNum , @PathVariable Integer diaryNum) {
		//System.out.println("test" + diaryNum + diaryRplNum);
		service.deleteReply(diaryRplNum);
		DietDiaryBoardVO dboard = DietDiaryBoardVO.builder().diaryNum(diaryNum).build();
		return new ResponseEntity<>(service.selectAll(dboard), HttpStatus.OK);
	}

}
