package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.CenterVO;

public interface CenterRepository extends CrudRepository<CenterVO, Integer>{
	@Query(value="select center_num, avg(cr_preference) AS gprefer from centerreview GROUP BY center_num", nativeQuery = true)
	public List<Object[]> getAllPreferences();
}
