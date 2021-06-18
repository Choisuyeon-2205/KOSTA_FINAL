package com.kosta.finalProject.persistences;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.ExerciseTypeRefVO;
import com.kosta.finalProject.models.ExerciseTypeRefVOId;


public interface ExerciseTypeRefRepository extends CrudRepository<ExerciseTypeRefVO, ExerciseTypeRefVOId>{

}
