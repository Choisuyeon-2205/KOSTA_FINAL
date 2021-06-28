package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;

public interface CurriculumRegisterRepository extends CrudRepository<CurriculumRegisterVO, CurriculumRegisterVOId>, QuerydslPredicateExecutor<CurriculumRegisterVO>{
	@Query(value="SELECT cu.curriculum_num, cu.curriculum_title FROM curriculumreg cur left outer join curriculum cu ON (cur.curriculum_num = cu.curriculum_num) "
			+ "WHERE cu.center_num=?1 AND cur.user_id=?2", nativeQuery = true)
	public List<Object[]> findByCurRegId(int center_num, String user_id);
	
	@Query(value="SELECT cu.* FROM curriculumreg cur left outer join curriculum cu ON (cur.curriculum_num = cu.curriculum_num) WHERE user_id=?1", nativeQuery = true)
	public List<Object[]> allFindByUserId(String user_id);
	
	@Query(value="SELECT cr.curriculum_num, u.user_id, u.user_name, u.nick_name, u.add_num, u.user_address1, u.user_address3, u.user_address2, u.user_phone, u.user_email FROM curriculumreg cr LEFT OUTER JOIN user_tb u ON (cr.user_id=u.user_id) WHERE cr.curriculum_num IN (SELECT curriculum_num FROM curriculum WHERE center_num= ?1)", nativeQuery = true)
	public List<Object[]> allFindByCenterNum(int center_num);
	
	@Query(value="SELECT cr.curriculum_num, u.user_id, u.user_name, u.nick_name, u.add_num, u.user_address1, u.user_address3, u.user_address2, u.user_phone, u.user_email FROM curriculumreg cr LEFT OUTER JOIN user_tb u ON (cr.user_id=u.user_id) WHERE cr.curriculum_num=?1", nativeQuery = true)
	public List<Object[]> allFindByCurriculumNum(int curriculum_num);
	
}
