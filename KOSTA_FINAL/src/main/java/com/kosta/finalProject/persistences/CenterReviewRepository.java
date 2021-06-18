package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.CenterReviewVO;

public interface CenterReviewRepository extends CrudRepository<CenterReviewVO, Integer>{
	@Query(value="SELECT * FROM centerreview WHERE center_num=?1", nativeQuery = true)
	public List<CenterReviewVO> findByCenterNum(int center_num);
	
	@Query(value="select avg(cr_preference) AS gprefer from centerreview where center_num=?1 GROUP BY center_num", nativeQuery = true)
	public double calAvgPreference(int center_num);
}
