package com.kosta.finalProject.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.ExerciseRecordId;
import com.kosta.finalProject.models.ExerciseRecordVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.ExerciseRecordRepository;
import com.querydsl.core.types.Predicate;

@Service
public class ExerciseRecordService {
	@Autowired
	ExerciseRecordRepository repo;
	
	public List<ExerciseRecordVO> selectAllWithPredicate(UserVO user, String startDate) {
		Predicate p = repo.makePredicate(user, startDate);
		List<ExerciseRecordVO> result = (List<ExerciseRecordVO>) repo.findAll(p);
		return result;
	}
	
	public List<ExerciseRecordVO> selectAll() {
		return (List<ExerciseRecordVO>) repo.findAll();
	}
	
	public List<ExerciseRecordVO> selectByUser(String userId) {
		return repo.selectByUserId(userId);
	}

	public ExerciseRecordVO selectByDateAndUser(String userId, Date exerciseDate) {
		return repo.selectByUserIdAndDate(userId, exerciseDate);
	}
	
	public int insertExerciseRecord(ExerciseRecordVO exercise) {
		int result = 0;
		try {
			repo.save(exercise);
			result = 1;
		} catch (Exception e) {
			return 0;
		}
		return result;
	}
	
	public int deleteExerciseRecord(ExerciseRecordId eid) {
		int result = 0;
		try {
			repo.deleteById(eid);
		} catch (Exception e) {
			return 0;
		}
		return result;
	}
	
	/*
	 * public Optional<ExerciseRecordVO> selectByDateAndUser(String userId, Date
	 * exerciseDate) { ExerciseRecordId exerciseId = new ExerciseRecordId();
	 * exerciseId.setExerciseDate(exerciseDate); UserVO user =
	 * UserVO.builder().userId(userId).build(); exerciseId.setUser(user); return
	 * repo.findById(exerciseId); }
	 */
}