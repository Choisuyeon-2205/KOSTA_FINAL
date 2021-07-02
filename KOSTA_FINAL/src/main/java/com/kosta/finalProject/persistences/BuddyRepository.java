package com.kosta.finalProject.persistences;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.BuddyVO;

public interface BuddyRepository extends CrudRepository<BuddyVO, Integer> {
	
	 @Query(value="SELECT * FROM buddy_tb WHERE user1_user_id=?1 OR user2_user_id=?1", nativeQuery = true)
	   public BuddyVO findUserid(String user_id);
}
