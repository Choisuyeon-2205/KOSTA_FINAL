package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.HealthReplyVO;

public interface HealthReplyRepository extends CrudRepository<HealthReplyVO, Integer>{
	
	public List<HealthReplyVO> findByHboard(HealthBoardVO hboard);
}
