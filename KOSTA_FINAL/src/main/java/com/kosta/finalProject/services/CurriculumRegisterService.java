package com.kosta.finalProject.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.ExerciseTypeRefVO;
import com.kosta.finalProject.models.ExerciseTypeRefVOId;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.persistences.CurriculumRegisterRepository;


@Service
public class CurriculumRegisterService {
	@Autowired
	CurriculumRegisterRepository curregRepo;
	
	public List<CurriculumVO> selectByCurRegId(int center_num, String user_id) {
		System.out.println("Center:"+center_num+", userid: "+user_id);
		List<Object[]> objects= curregRepo.findByCurRegId(center_num, user_id);
		System.out.println("service"+objects);
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
	
	public List<CurriculumVO> selectAllByCurRegId(String user_id) {
		List<Object[]> objects= curregRepo.allFindByUserId(user_id);
		List<CurriculumVO> curriculums= new ArrayList<>();
		objects.forEach(arr->{
			TrainerVO trainer= TrainerVO.builder().trainerNum((Integer)arr[11]).build();
			
			ExerciseTypeRefVOId etrefvoid= new ExerciseTypeRefVOId();
			CenterVO center= CenterVO.builder().centerNum((Integer)arr[9]).build();
			ExerciseTypeVO etype= ExerciseTypeVO.builder().exerciseTypeNum((Integer)arr[10]).build();
			etrefvoid.setCenter(center);
			etrefvoid.setEtype(etype);
			
			ExerciseTypeRefVO etrefvo= ExerciseTypeRefVO.builder()
							.id(etrefvoid)
							.build();
			
			CurriculumVO curriculum= CurriculumVO.builder()
					.curriculumNum((Integer)arr[0])
					.curriculumAll((Integer)arr[1])
					.curriculumDate((String)arr[2])
					.curriculumFirst((Timestamp)arr[3])
					.curriculumInfo((String)arr[4])
					.curriculumState((Integer)arr[5])
					.curriculumTime((String)arr[6])
					.curriculumTitle((String)arr[7])
					.curriculumUpdate((Timestamp)arr[8])
					.etyperef(etrefvo)
					.trainer(trainer)
					.build();
			curriculums.add(curriculum);
		});
		return curriculums;
	}
	
	public List<Object[]> selectByCenterNum(int center_num) {
		return curregRepo.allFindByCenterNum(center_num);
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
