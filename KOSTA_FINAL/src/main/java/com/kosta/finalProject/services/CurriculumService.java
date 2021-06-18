package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.persistences.CurriculumRepository;

@Service
public class CurriculumService {
	@Autowired
	CurriculumRepository curRepo;
	
	public CurriculumVO selectById(int curriculumNum) {
		return curRepo.findById((Integer)curriculumNum).get();
	}
	
	public List<CurriculumVO> selectByCenter(int cnum) {
		return curRepo.findByCenterNum(cnum);
	}
	
	public List<CurriculumVO> selectAll() {
		return (List<CurriculumVO>)curRepo.findAll();
	}
	
	public int getState(int curriculum_num) {
		return curRepo.findCurrent(curriculum_num);
	}
	
	public CurriculumVO insertCurriculum(CurriculumVO curriculum) {
		return curRepo.save(curriculum);
	}
	
	public CurriculumVO updateCurriculum(CurriculumVO curriculum) {
		return curRepo.save(curriculum);
	}
	
	public int deleteCurriculum(int curriculumNum) {
		//삭제하기
		int ret=0;
		try {
			curRepo.deleteById((Integer)curriculumNum);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
