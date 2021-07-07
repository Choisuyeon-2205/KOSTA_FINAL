package com.kosta.finalProject.controllers;

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

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.HealthReplyVO;
import com.kosta.finalProject.models.SharingBoardVO;
import com.kosta.finalProject.models.SharingReplyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.SharingBoardService;
import com.kosta.finalProject.services.SharingReplyService;
import com.kosta.finalProject.services.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/replies/sboard/*")
public class SharingBoardReplyController {
	
	@Autowired
	SharingReplyService service;
	@Autowired
	UserService uservice;
	@Autowired
	SharingBoardService shareservice;
	
	@GetMapping("/all/{shareNum}")
	public ResponseEntity<List<SharingReplyVO>> selectAll(@PathVariable int shareNum) {
		SharingBoardVO sboard = SharingBoardVO.builder().shareNum(shareNum).build();
		return new ResponseEntity<>(service.selectAll(sboard), HttpStatus.OK);
	}
	
	@GetMapping("/{shareRplNum}")
	public ResponseEntity<SharingReplyVO> viewReply(@PathVariable Integer shareRplNum) {
		return new ResponseEntity<>(service.selectById(shareRplNum), HttpStatus.OK);
	}
	
	@PostMapping("/{shareNum}")
	public ResponseEntity<List<SharingReplyVO>> addReply(@PathVariable Integer shareNum, Authentication authentication, @RequestBody SharingReplyVO sreply) {

		log.info("addReply-------------------------------------------------------");
		log.info("shareNum" + shareNum);
		log.info("sreply" + sreply);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());

		SharingBoardVO sboard = shareservice.selectById(shareNum);
		sreply.setSboard(sboard);
		sreply.setUser(user);
		service.updateOrInsert(sreply);
		return new ResponseEntity<>(service.selectAll(sboard), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{shareNum}/{shareRplNum}")
	public ResponseEntity<List<SharingReplyVO>> deleteByRplno(@PathVariable Integer shareRplNum, @PathVariable Integer shareNum) {
		service.deleteReply(shareRplNum);
		SharingBoardVO sboard = SharingBoardVO.builder().shareNum(shareNum).build();
		return new ResponseEntity<>(service.selectAll(sboard), HttpStatus.OK);
	}
}
