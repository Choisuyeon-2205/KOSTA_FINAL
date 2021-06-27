package com.kosta.finalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.ExerciseTypeRefVO;
import com.kosta.finalProject.models.ExerciseTypeRefVOId;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.persistences.ExerciseTypeRefRepository;
import com.kosta.finalProject.persistences.ExerciseTypeRepository;

@Service
public class ExerciseTypeRefService {
	@Autowired
	ExerciseTypeRepository etRepository;
	@Autowired
	ExerciseTypeRefRepository etrRepository;
	
	public List<ExerciseTypeVO> selectByCenterId(int center_num) {
		List<Object[]> objects= etRepository.getByCenterNum(center_num);
		List<ExerciseTypeVO> etypes= new ArrayList<>();
		objects.forEach(arr->{
			ExerciseTypeVO etype= ExerciseTypeVO.builder()
					.exerciseTypeNum((Integer)arr[0])
					.exerciseTypeName((String)arr[1])
					.build();
			etypes.add(etype);
		});
		return etypes;
	}
	
	public ExerciseTypeRefVO insertExerciseTypeRef(ExerciseTypeRefVO etyperef) {
		return etrRepository.save(etyperef);
	}
	
	public ExerciseTypeRefVO selectByExerciseTypeRefVOId(ExerciseTypeRefVOId etrvoid) {
		ExerciseTypeRefVO vo = null;
		try {
			vo = etrRepository.findById(etrvoid).orElseThrow();
		}catch(Exception aa) {
			return null;
		}
		return vo;
	}
}
