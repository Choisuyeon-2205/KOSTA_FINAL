package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.ExerciseInfoBoardVO;
import com.kosta.finalProject.models.PageVO;
import com.kosta.finalProject.persistences.ExerciseInfoBoardRepository;
import com.querydsl.core.types.Predicate;

@Service
public class ExerciseInfoBoardService {

	@Autowired
	ExerciseInfoBoardRepository repo;

	// page
	public Page<ExerciseInfoBoardVO> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate(pvo.getType(), pvo.getKeyword());
		Pageable pageable = pvo.makePaging(0, "infoNum");
		Page<ExerciseInfoBoardVO> result = repo.findAll(p, pageable);
		return result;
	}

	// list조회
	public List<ExerciseInfoBoardVO> selectAll() {
		return (List<ExerciseInfoBoardVO>) repo.findAll();
	}

	// 아이디로 찾기
	public ExerciseInfoBoardVO selectById(Integer infoNum) {
		return repo.findById(infoNum).get();
	}

	// 삽입
	public ExerciseInfoBoardVO insertBoard(ExerciseInfoBoardVO board) {
		return repo.save(board);
	}

	// 수정
	public ExerciseInfoBoardVO updateBoard(ExerciseInfoBoardVO board) {
		return repo.save(board);
	}

	// 제거
	public int deleteBoard(Integer infoNum) {
		int ret = 0;
		try {
			repo.deleteById(infoNum);
			ret = 1;
		} catch (Exception ex) {
		}
		return ret;
	}

}
