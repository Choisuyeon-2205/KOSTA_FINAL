package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.PageVO;
import com.kosta.finalProject.persistences.DietDiaryBoardRepository;
import com.querydsl.core.types.Predicate;

@Service
public class DietDiaryBoardService {

	@Autowired
	DietDiaryBoardRepository repo;

	//page
	public Page<DietDiaryBoardVO> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate(pvo.getType(), pvo.getKeyword());
		
		//makePaging(방향, sort할 field)
		Pageable pageable = pvo.makePaging(0, "diaryNum");
		Page<DietDiaryBoardVO> result = repo.findAll(p, pageable);
		return result;
	}
	
	
	//list조회
	public List<DietDiaryBoardVO> selectAll() {
		return (List<DietDiaryBoardVO>)repo.findAll();
	}
	
	//아이디로 찾기
	public DietDiaryBoardVO selectById(Integer diaryNum) {
		return repo.findById(diaryNum).get();
	}
	//삽입
	public DietDiaryBoardVO insertBoard(DietDiaryBoardVO board) {
		return repo.save(board);
	}
	//수정
	public DietDiaryBoardVO updateBoard(DietDiaryBoardVO board) {
		return repo.save(board);
	}
	//제거
	public int deleteBoard(Integer diaryNum) {
		int ret = 0;
		try {
			repo.deleteById(diaryNum);
			ret = 1;
		} catch (Exception ex) {
		}
		return ret;
	}
}
