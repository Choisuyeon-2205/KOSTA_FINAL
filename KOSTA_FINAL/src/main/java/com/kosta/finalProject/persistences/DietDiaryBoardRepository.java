package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.QDietDiaryBoardVO;
import com.kosta.finalProject.models.UserVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface DietDiaryBoardRepository extends CrudRepository<DietDiaryBoardVO, Integer>, QuerydslPredicateExecutor<DietDiaryBoardVO> {

	
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QDietDiaryBoardVO board = QDietDiaryBoardVO.dietDiaryBoardVO;
		builder.and(board.diaryNum.gt(0)); //and diaryNum>0
		if(type==null) return builder;
		switch (type) {
		case "title":
			builder.and(board.title.like("%" + keyword + "%")); //and title like '%?%'
			break;
		case "content":
			builder.and(board.content.like("%" + keyword + "%")); //and content like '%?%'
			break;
		case "writer":
			builder.and(board.user.nickName.like("%" + keyword + "%")); //and writer like '%?%'
			break;
		default:
			break;
		}
		return builder;
	}
	
	
	public Page<DietDiaryBoardVO> findByContent(String content, Pageable page);

	public Page<DietDiaryBoardVO> findAll(Predicate p, Pageable pageable);
	
}
