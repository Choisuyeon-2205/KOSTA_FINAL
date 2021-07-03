package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.MealCerfVO;
import com.kosta.finalProject.models.MealId;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.MealCerfRepository;
import com.querydsl.core.types.Predicate;

@Service
public class MealService {
	@Autowired
	MealCerfRepository repo;
	
	public List<MealCerfVO> selectAllWithPredicate(UserVO user, String startDate) {
		Predicate p = repo.makePredicate(user, startDate);
		List<MealCerfVO> result = (List<MealCerfVO>) repo.findAll(p);
		return result;
	}
	
	public int insertMealCerf(MealCerfVO meal) {
		int result = 0;
		try {
			repo.save(meal);
			result = 1;
		} catch (Exception e) {
			return 0;
		}
		return result;
	}
	
	public MealCerfVO selectById(MealId mealId) {
		return repo.findById(mealId).get();
	}
	
	public int deleteMealCerf(MealId mealId) {
		int result = 0;
		try {
			repo.deleteById(mealId);
			result = 1;
		} catch (Exception e) {
			return 0;
		}
		return result;
	}
	
	public MealCerfVO updateMealCerf(MealCerfVO mealCerf) {
		return repo.save(mealCerf);
	}
}

