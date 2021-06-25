package com.kosta.finalProject.persistences;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.BuddyVO;

public interface BuddyRepository extends CrudRepository<BuddyVO, Integer> {
	
//	@Query("SELECT ? FROM UserBodyVO b INNER JOIN b.")
//	public List<UserBodyVO> 
}
