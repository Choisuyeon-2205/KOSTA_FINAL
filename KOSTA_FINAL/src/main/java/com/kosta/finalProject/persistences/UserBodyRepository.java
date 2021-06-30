package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.UserBodyVO;

public interface UserBodyRepository extends CrudRepository<UserBodyVO, Integer>, JpaRepository<UserBodyVO, Integer> {

	@Query(value = "SELECT * FROM user_body_tb WHERE body_num = ( SELECT max(body_num) FROM user_body_tb WHERE user_Id = ?1 )", nativeQuery = true)
	public UserBodyVO findByUser(String userid);
	
	@Query(value = "SELECT * FROM user_body_tb WHERE buddy_check=1 AND bmi_group=?2 AND user_id!=?1", nativeQuery = true)
	List<UserBodyVO> findIsBuddy(String userId, int bmiGroup);
	
	@Query(value = "SELECT * FROM user_body_tb WHERE user_id = ?1 ORDER BY insert_date desc", nativeQuery = true)
	public List<UserBodyVO> findByGraph(String userid);
}
