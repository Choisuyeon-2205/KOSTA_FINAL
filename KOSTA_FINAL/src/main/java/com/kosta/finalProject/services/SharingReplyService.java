package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.HealthReplyVO;
import com.kosta.finalProject.models.SharingBoardVO;
import com.kosta.finalProject.models.SharingReplyVO;
import com.kosta.finalProject.persistences.SharingReplyRepository;

@Service
public class SharingReplyService {
	
	@Autowired
	SharingReplyRepository repo;
	
	public List<SharingReplyVO> selectAll(SharingBoardVO sboard) {
		return (List<SharingReplyVO>)repo.findBySboard(sboard);
	}
	
	public SharingReplyVO selectAll (Integer shareRplNum) {
		return repo.findById(shareRplNum).get();
	}
	
	public SharingReplyVO selectById(Integer shareRplNum) {
		return repo.findById(shareRplNum).get();
	}
	public SharingReplyVO updateOrInsert(SharingReplyVO reply) {
		return repo.save(reply);
	}
	
	public int deleteReply(Integer shareRplNum) {
		int ret=0;
		try {
		repo.deleteById(shareRplNum);
		ret=1;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ret;
	}
}
