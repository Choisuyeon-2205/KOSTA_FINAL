package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.ExerciseRecordVO;
import com.kosta.finalProject.persistences.ExerciseRecordRepository;

@Service
public class ExerciseRecordService {
	@Autowired
	ExerciseRecordRepository repo;
	
	public List<ExerciseRecordVO> selectAll() {
		return (List<ExerciseRecordVO>) repo.findAll();
	}
}
