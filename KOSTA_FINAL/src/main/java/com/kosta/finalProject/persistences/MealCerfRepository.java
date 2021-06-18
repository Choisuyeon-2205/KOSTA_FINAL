package com.kosta.finalProject.persistences;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.MealCerfVO;
import com.kosta.finalProject.models.MealId;

public interface MealCerfRepository extends CrudRepository<MealCerfVO, MealId>{

}
