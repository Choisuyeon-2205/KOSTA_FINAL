package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.CenterReviewVO;
import com.kosta.finalProject.persistences.CenterReviewRepository;


@Service
public class CenterReviewService {
	@Autowired
	CenterReviewRepository crRepo;
	
	public CenterReviewVO selectById(int crNum) {
		return crRepo.findById((Integer)crNum).get();
	}
	
	public List<CenterReviewVO> selectByCenter(int cnum) {
		return crRepo.findByCenterNum(cnum);
	}
	
	public double calAvgPreference(int cnum) {
		return crRepo.calAvgPreference(cnum);
	}
	
	public List<CenterReviewVO> selectAll() {
		return (List<CenterReviewVO>)crRepo.findAll();
	}
	
	public CenterReviewVO updateOrInsert(CenterReviewVO etype) {
		return crRepo.save(etype);
	}
	
	
	public int deleteReview(int crNum) {
		//삭제하기
		int ret=0;
		try {
			crRepo.deleteById(crNum);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
