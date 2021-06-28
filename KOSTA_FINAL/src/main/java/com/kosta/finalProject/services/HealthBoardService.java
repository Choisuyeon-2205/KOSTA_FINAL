package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.PageVO;
import com.kosta.finalProject.persistences.HealthBoardRepository;
import com.querydsl.core.types.Predicate;

@Service
public class HealthBoardService {
	@Autowired
	HealthBoardRepository repo;

	// page
	public Page<HealthBoardVO> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate(pvo.getType(), pvo.getKeyword());
		Pageable pageable = pvo.makePaging(0, "healthNum");
		Page<HealthBoardVO> result = repo.findAll(p, pageable);
		return result;
	}

	// list조회
	public List<HealthBoardVO> selectAll() {
		return (List<HealthBoardVO>) repo.findAll();
	}

	// 아이디로 찾기
	public HealthBoardVO selectById(Integer healthNum) {
		return repo.findById(healthNum).get();
	}

	// 삽입
	public HealthBoardVO insertBoard(HealthBoardVO board) {
		return repo.save(board);
	}

	// 수정
	public HealthBoardVO updateBoard(HealthBoardVO board) {
		return repo.save(board);
	}

	// 제거
	public int deleteBoard(Integer healthNum) {
		int ret = 0;
		try {
			repo.deleteById(healthNum);
			ret = 1;
		} catch (Exception ex) {
		}
		return ret;
	}
}
