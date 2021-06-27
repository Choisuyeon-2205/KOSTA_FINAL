package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.ExerciseTypeVO;

public interface ExerciseTypeRepository extends CrudRepository<ExerciseTypeVO, Integer>{
	@Query(value="SELECT et.* FROM exercisetype et right OUTER JOIN exercisetyperef etr ON (et.exercise_type_num= etr.exercise_type_num) WHERE etr.center_num=?1", nativeQuery = true)
	public List<Object[]> getByCenterNum(int center_num);
}
