package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kosta.finalProject.models.UserVO;

public interface UserRepository extends CrudRepository<UserVO, String> {
	List<UserVO> findByUserId(String userId);
	
	@Query("select user from UserVO user ")
	public List<UserVO> selectAll();
	
	@Query("select u from UserVO u where u.userId like %:userId% ")
	public List<UserVO> selectById(@Param("userId") String userId);
	
//	@Query("select ")
//	public List<UserVO> selectByAddress();
}
