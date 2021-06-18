package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.persistences.ExerciseTypeRepository;

@Service
public class ExerciseTypeService {
	@Autowired
	ExerciseTypeRepository etypeRepo;
	
	public ExerciseTypeVO selectByNum(int exerciseTypeNum) {
		return etypeRepo.findById((Integer)exerciseTypeNum).get();
	}
	
	public List<ExerciseTypeVO> selectAll() {
		return (List<ExerciseTypeVO>)etypeRepo.findAll();
	}
	
	public ExerciseTypeVO insertEtype(ExerciseTypeVO etype) {
		return etypeRepo.save(etype);
	}
	
	public ExerciseTypeVO updateEtype(ExerciseTypeVO etype) {
		return etypeRepo.save(etype);
	}
	
	public int deleteEtype(int exerciseTypeNum) {
		//삭제하기
		int ret=0;
		try {
			etypeRepo.deleteById((Integer)exerciseTypeNum);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
