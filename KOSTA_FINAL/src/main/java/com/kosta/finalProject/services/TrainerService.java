package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.persistences.TrainerRepository;

@Service
public class TrainerService {
	@Autowired
	TrainerRepository trainerRepo;
	
	public TrainerVO selectById(int trainer_num) {
		return trainerRepo.findById((Integer)trainer_num).get();
	}
	
	public List<TrainerVO> selectByCenter(int center_num) {
		return (List<TrainerVO>)trainerRepo.findByCenterNum(center_num);
	}
	
	public List<TrainerVO> selectAll() {
		return (List<TrainerVO>)trainerRepo.findAll();
	}
	
	public TrainerVO insertTrainer(TrainerVO trainer) {
		return trainerRepo.save(trainer);
	}
	
	public TrainerVO updateTrainer(TrainerVO trainer) {
		return trainerRepo.save(trainer);
	}
	
	public int deleteTrainer(int trainer_num) {
		//삭제하기
		int ret=0;
		try {
			trainerRepo.deleteById((Integer)trainer_num);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
