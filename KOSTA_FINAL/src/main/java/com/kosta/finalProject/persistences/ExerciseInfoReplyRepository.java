package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.ExerciseInfoBoardVO;
import com.kosta.finalProject.models.ExerciseInfoReplyVO;

public interface ExerciseInfoReplyRepository extends CrudRepository<ExerciseInfoReplyVO, Integer> {

	public List<ExerciseInfoReplyVO> findByEboard(ExerciseInfoBoardVO eboard);
}
