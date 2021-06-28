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
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.HealthBoardService;
import com.kosta.finalProject.services.HealthReplyService;
import com.kosta.finalProject.services.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/replies/hboard/*")
public class HealthBoardReplyController {

	@Autowired
	HealthReplyService service;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	HealthBoardService healthservice;
	
	@GetMapping("/all/{healthNum}")
	public ResponseEntity<List<HealthReplyVO>> selectAll(@PathVariable int healthNum) {
		HealthBoardVO eboard = HealthBoardVO.builder().healthNum(healthNum).build();
		return new ResponseEntity<>(service.selectAll(eboard), HttpStatus.OK);
	}
	
	@GetMapping("/{healthRplNum}")
	public ResponseEntity<HealthReplyVO> viewReply(@PathVariable Integer healthRplNum) {
		return new ResponseEntity<>(service.selectById(healthRplNum), HttpStatus.OK);
	}
	
	@PostMapping("/{healthNum}")
	public ResponseEntity<List<HealthReplyVO>> addReply(@PathVariable Integer healthNum,
			Authentication authentication, @RequestBody HealthReplyVO hreply) {

		log.info("addReply-------------------------------------------------------");
		log.info("healthNum" + healthNum);
		log.info("hreply" + hreply);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());

		HealthBoardVO eboard = healthservice.selectById(healthNum);
		hreply.setHboard(eboard);
		hreply.setUser(user);
		service.updateOrInsert(hreply);
		return new ResponseEntity<>(service.selectAll(eboard), HttpStatus.CREATED);
	}
		
	@DeleteMapping("/{healthNum}/{healthRplNum}")
	public ResponseEntity<List<HealthReplyVO>> deleteByRplno(@PathVariable Integer healthRplNum,
			@PathVariable Integer healthNum) {
		service.deleteReply(healthRplNum);
		HealthBoardVO hboard = HealthBoardVO.builder().healthNum(healthNum).build();
		return new ResponseEntity<>(service.selectAll(hboard), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
