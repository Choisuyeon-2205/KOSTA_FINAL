package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.TrainerVO;

public interface CurriculumRepository extends CrudRepository<CurriculumVO, Integer>{

	@Query(value="SELECT * FROM curriculum WHERE center_num=?1", nativeQuery = true)
	public List<CurriculumVO> findByCenterNum(int center_num);
	
	@Query(value="SELECT curriculum_state FROM curriculum WHERE curriculum_num=?1", nativeQuery = true)
	public int findCurrent(int curriculum_num);
}
