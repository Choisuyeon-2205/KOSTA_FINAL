package com.kosta.finalProject.persistences;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kosta.finalProject.models.UserVO;

public interface UserRepository extends CrudRepository<UserVO, String>,JpaRepository<UserVO, String> {
	List<UserVO> findByUserId(String userId);
	Optional<UserVO> findByUserName(String name);
	Optional<UserVO> findByUserEmail(String email);
	
	@Query("select user from UserVO user ")
	public List<UserVO> selectAll();
	
//	@Query("select u from UserVO u where u.userId like %:userId% ")
//	public List<UserVO> selectById(@Param("userId") String userId);
	
//	@Query("select ")
//	public List<UserVO> selectByAddress();
}
