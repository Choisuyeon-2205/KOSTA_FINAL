package com.kosta.finalProject.persistences;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.ExerciseRecordId;
import com.kosta.finalProject.models.ExerciseRecordVO;

public interface ExerciseRecordRepository extends CrudRepository<ExerciseRecordVO, ExerciseRecordId> {

}
