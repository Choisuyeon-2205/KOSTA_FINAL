package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.ExerciseInfoBoardVO;
import com.kosta.finalProject.models.ExerciseInfoReplyVO;
import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.HealthReplyVO;
import com.kosta.finalProject.persistences.HealthReplyRepository;

@Service
public class HealthReplyService {
	
	@Autowired
	HealthReplyRepository repo;
	
	public List<HealthReplyVO> selectAll(HealthBoardVO hboard) {
		return (List<HealthReplyVO>)repo.findByHboard(hboard);
	}
	
	public HealthReplyVO selectAll (Integer healthRplNum) {
		return repo.findById(healthRplNum).get();
	}
	
	public HealthReplyVO selectById(Integer healthRplNum) {
		return repo.findById(healthRplNum).get();
	}
	public HealthReplyVO updateOrInsert(HealthReplyVO reply) {
		return repo.save(reply);
	}
	
	public int deleteReply(Integer healthRplNum) {
		int ret=0;
		try {
		repo.deleteById(healthRplNum);
		ret=1;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ret;
	}
	
	
}
