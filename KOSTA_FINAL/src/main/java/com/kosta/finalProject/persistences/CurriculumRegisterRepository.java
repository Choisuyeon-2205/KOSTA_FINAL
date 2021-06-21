package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;

public interface CurriculumRegisterRepository extends CrudRepository<CurriculumRegisterVO, CurriculumRegisterVOId>{
	@Query(value="SELECT cu.curriculum_num, cu.curriculum_title FROM curriculumreg cur left outer join curriculum cu ON (cur.curriculum_num = cu.curriculum_num) "
			+ "WHERE center_num=?1 AND user_id=?2", nativeQuery = true)
	public List<Object[]> findByCurRegId(int center_num, String user_id);
	
	@Query(value="SELECT cu.* FROM curriculumreg cur left outer join curriculum cu ON (cur.curriculum_num = cu.curriculum_num) WHERE user_id=?1", nativeQuery = true)
	public List<Object[]> allFindByUserId(String user_id);
}
