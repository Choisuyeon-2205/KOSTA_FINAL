package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.finalProject.models.ExerciseRecordVO;
import com.kosta.finalProject.services.ExerciseRecordService;

@RestController
public class ExerciseRecordController {
	@Autowired
	ExerciseRecordService service;
	
	@GetMapping("/exercise/records")
	public List<ExerciseRecordVO> selectAll(Model model) {
		List<ExerciseRecordVO> result = service.selectAll();
		return result;
	}
}
