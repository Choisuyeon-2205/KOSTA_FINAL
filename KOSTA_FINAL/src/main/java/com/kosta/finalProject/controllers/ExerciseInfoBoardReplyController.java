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

import com.kosta.finalProject.models.ExerciseInfoBoardVO;
import com.kosta.finalProject.models.ExerciseInfoReplyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.ExerciseInfoBoardService;
import com.kosta.finalProject.services.ExerciseInfoReplyService;
import com.kosta.finalProject.services.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/replies/eboard/*")
public class ExerciseInfoBoardReplyController {

	@Autowired
	ExerciseInfoReplyService service;
	@Autowired
	UserService uservice;
	@Autowired
	ExerciseInfoBoardService infoservice;

	@GetMapping("/all/{infoNum}")
	public ResponseEntity<List<ExerciseInfoReplyVO>> selectAll(@PathVariable int infoNum) {
		ExerciseInfoBoardVO eboard = ExerciseInfoBoardVO.builder().infoNum(infoNum).build();
		return new ResponseEntity<>(service.selectAll(eboard), HttpStatus.OK);
	}

	@GetMapping("/{infoRplNum}")
	public ResponseEntity<ExerciseInfoReplyVO> viewReply(@PathVariable Integer infoRplNum) {
		return new ResponseEntity<>(service.selectById(infoRplNum), HttpStatus.OK);
	}

	@PostMapping("/{infoNum}")
	public ResponseEntity<List<ExerciseInfoReplyVO>> addReply(@PathVariable Integer infoNum, Authentication authentication, @RequestBody ExerciseInfoReplyVO ereply) {

		log.info("addReply-------------------------------------------------------");
		log.info("infoNum" + infoNum);
		log.info("ereply" + ereply);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());

		ExerciseInfoBoardVO eboard = infoservice.selectById(infoNum);
		ereply.setEboard(eboard);
		ereply.setUser(user);
		service.updateOrInsert(ereply);
		return new ResponseEntity<>(service.selectAll(eboard), HttpStatus.CREATED);
	}

	@DeleteMapping("/{infoNum}/{infoRplNum}")
	public ResponseEntity<List<ExerciseInfoReplyVO>> deleteByRplno(@PathVariable Integer infoRplNum, @PathVariable Integer infoNum) {
		service.deleteReply(infoRplNum);
		ExerciseInfoBoardVO eboard = ExerciseInfoBoardVO.builder().infoNum(infoNum).build();
		return new ResponseEntity<>(service.selectAll(eboard), HttpStatus.OK);
	}

}
