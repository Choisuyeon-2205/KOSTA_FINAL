package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.PageVO;
import com.kosta.finalProject.models.SharingBoardVO;
import com.kosta.finalProject.persistences.SharingBoardRepository;
import com.querydsl.core.types.Predicate;

@Service
public class SharingBoardService {
	
	@Autowired
	SharingBoardRepository repo;
	
		// page
		public Page<SharingBoardVO> selectAll(PageVO pvo) {
			Predicate p = repo.makePredicate(pvo.getType(), pvo.getKeyword());
			Pageable pageable = pvo.makePaging(0, "shareNum");
			Page<SharingBoardVO> result = repo.findAll(p, pageable);
			return result;
		}

		// list조회
		public List<SharingBoardVO> selectAll() {
			return (List<SharingBoardVO>) repo.findAll();
		}

		// 아이디로 찾기
		public SharingBoardVO selectById(Integer shareNum) {
			return repo.findById(shareNum).get();
		}

		// 삽입
		public SharingBoardVO insertBoard(SharingBoardVO board) {
			return repo.save(board);
		}

		// 수정
		public SharingBoardVO updateBoard(SharingBoardVO board) {
			return repo.save(board);
		}

		// 제거
		public int deleteBoard(Integer shareNum) {
			int ret = 0;
			try {
				repo.deleteById(shareNum);
				ret = 1;
			} catch (Exception ex) {
			}
			return ret;
		}
}
