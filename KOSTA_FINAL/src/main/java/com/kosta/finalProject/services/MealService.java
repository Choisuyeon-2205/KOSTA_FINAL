package com.kosta.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.MealCerfVO;
import com.kosta.finalProject.models.MealId;
import com.kosta.finalProject.persistences.MealCerfRepository;

@Service
public class MealService {
	@Autowired
	MealCerfRepository repo;
	
	public MealCerfVO insertMealCerf(MealCerfVO mealCerf) {
		return repo.save(mealCerf);
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
