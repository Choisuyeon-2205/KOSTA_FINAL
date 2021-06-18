package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.DietDiaryReplyVO;

public interface DietDiaryReplyRepository extends CrudRepository<DietDiaryReplyVO, Integer> {
	
	
}
