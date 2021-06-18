package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.persistences.CenterRepository;

@Service
public class CenterService {
	@Autowired
	CenterRepository centerRepo;
	
	public CenterVO selectById(int center_num) {
		return centerRepo.findById((Integer)center_num).get();
	}
	
	public List<CenterVO> selectAll() {
		return (List<CenterVO>)centerRepo.findAll();
	}
	
	public CenterVO insertCenter(CenterVO center) {
		return centerRepo.save(center);
	}
	
	public CenterVO updateCenter(CenterVO center) {
		return centerRepo.save(center);
	}
	
	public int deleteCenter(int center_num) {
		//삭제하기
		int ret=0;
		try {
			centerRepo.deleteById((Integer)center_num);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
