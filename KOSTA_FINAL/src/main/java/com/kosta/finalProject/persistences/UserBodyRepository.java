package com.kosta.finalProject.persistences;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;

public interface UserBodyRepository extends CrudRepository<UserBodyVO, String>, JpaRepository<UserBodyVO, String> {

	@Query(value = "SELECT * FROM user_body_tb WHERE body_num = ( SELECT max(body_num) FROM user_body_tb WHERE user_Id = ?1 )", nativeQuery = true)
	public UserBodyVO findByUser(String userid);
	
//	Optional<UserBodyVO> findByBMI(double bmi);
//	@Query(value = "SELECT * FROM user_body_tb b1, user_body_tb b2 WHERE b1.bmi_group = b2.bmi_group AND b1.buddy_check != 1 ORDER BY b1.bmi_group ASC ")
//	List<UserBodyVO> selectAll();
}
