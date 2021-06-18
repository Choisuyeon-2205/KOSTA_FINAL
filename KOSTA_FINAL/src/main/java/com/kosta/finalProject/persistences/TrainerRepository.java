package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.TrainerVO;


public interface TrainerRepository extends CrudRepository<TrainerVO, Integer>, QuerydslPredicateExecutor<TrainerVO>{
	
	@Query(value="select * from trainer where center_num=?1 order by 1", nativeQuery = true)
	public List<TrainerVO> findByCenterNum(int center_num);
}
