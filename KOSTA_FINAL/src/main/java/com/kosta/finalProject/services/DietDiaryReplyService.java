package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kosta.finalProject.models.DietDiaryReplyVO;
import com.kosta.finalProject.persistences.DietDiaryReplyRepository;

@Service
public class DietDiaryReplyService {
	
	@Autowired
	DietDiaryReplyRepository repo;
	
	
	public DietDiaryReplyVO selectAll (Integer diaryRplNum) {
		return repo.findById(diaryRplNum).get();
	}
	
	public DietDiaryReplyVO selectById(Integer diaryRplNum) {
		return repo.findById(diaryRplNum).get();
	}

	public DietDiaryReplyVO updateOrInsert(DietDiaryReplyVO reply) {
		return repo.save(reply);
	}
	
	public int deleteReply(Integer diaryRplNum) {
		int ret=0;
		try {
		repo.deleteById(diaryRplNum);
		ret=1;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ret;
	}
	
}
