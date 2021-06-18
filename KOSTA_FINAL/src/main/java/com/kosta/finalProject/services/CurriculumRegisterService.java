package com.kosta.finalProject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.persistences.CurriculumRegisterRepository;


@Service
public class CurriculumRegisterService {
	@Autowired
	CurriculumRegisterRepository curregRepo;
	
	public List<CurriculumVO> selectByCurRegId(int center_num, String user_id) {
		List<Object[]> objects= curregRepo.findByCurRegId(center_num, user_id);
		List<CurriculumVO> curriculums= new ArrayList<>();
		objects.forEach(arr->{
			CurriculumVO curriculum= CurriculumVO.builder()
					.curriculumNum((Integer)arr[0])
					.curriculumTitle((String)arr[1])
					.build();
			curriculums.add(curriculum);
		});
		return curriculums;
	}
	
	public CurriculumRegisterVO selectById(CurriculumRegisterVOId crid) {
		return curregRepo.findById(crid).orElse(null);
	}

	public List<CurriculumRegisterVO> selectAll() {
		return (List<CurriculumRegisterVO>)curregRepo.findAll();
	}
	
	public CurriculumRegisterVO insertCurriculumRegister(CurriculumRegisterVO curreg) {
		return curregRepo.save(curreg);
	}
	
	public CurriculumRegisterVO updateCurriculumRegister(CurriculumRegisterVO curreg) {
		return curregRepo.save(curreg);
	}
	
	public int deleteCurriculumRegister(CurriculumRegisterVOId crid) {
		//삭제하기
		int ret=0;
		try {
			curregRepo.deleteById(crid);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
