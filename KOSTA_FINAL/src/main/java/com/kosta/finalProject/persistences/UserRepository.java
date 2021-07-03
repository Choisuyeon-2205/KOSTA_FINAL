package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.UserVO;

public interface UserRepository extends CrudRepository<UserVO, String>,JpaRepository<UserVO, String> {
	List<UserVO> findByUserId(String userId);
	
	@Query("select user from UserVO user ")
	public List<UserVO> selectAll();
	

}
