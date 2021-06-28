package com.kosta.finalProject.persistences;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.QHealthBoardVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface HealthBoardRepository extends CrudRepository<HealthBoardVO, Integer>, QuerydslPredicateExecutor<HealthBoardVO> {

	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QHealthBoardVO board = QHealthBoardVO.healthBoardVO;
		builder.and(board.healthNum.gt(0)); //and healthNum>0
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
	
	public Page<HealthBoardVO> findByContent(String content, Pageable page);
	
	public Page<HealthBoardVO> findAll(Predicate p, Pageable pageable);
}
