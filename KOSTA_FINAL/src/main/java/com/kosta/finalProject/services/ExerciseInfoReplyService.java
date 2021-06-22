package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.ExerciseInfoBoardVO;
import com.kosta.finalProject.models.ExerciseInfoReplyVO;
import com.kosta.finalProject.persistences.ExerciseInfoReplyRepository;

@Service
public class ExerciseInfoReplyService {

	@Autowired
	ExerciseInfoReplyRepository repo;
	
	public List<ExerciseInfoReplyVO> selectAll(ExerciseInfoBoardVO eboard) {
		return (List<ExerciseInfoReplyVO>)repo.findByEboard(eboard);
	}
	
	public ExerciseInfoReplyVO selectAll (Integer infoRplNum) {
		return repo.findById(infoRplNum).get();
	}
	
	public ExerciseInfoReplyVO selectById(Integer infoRplNum) {
		return repo.findById(infoRplNum).get();
	}
	
	public ExerciseInfoReplyVO updateOrInsert(ExerciseInfoReplyVO reply) {
		return repo.save(reply);
	}
	
	public int deleteReply(Integer infoRplNum) {
		int ret=0;
		try {
		repo.deleteById(infoRplNum);
		ret=1;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ret;
	}
}
