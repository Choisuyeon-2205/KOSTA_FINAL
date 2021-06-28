package com.kosta.finalProject.persistences;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.QSharingBoardVO;
import com.kosta.finalProject.models.SharingBoardVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface SharingBoardRepository extends CrudRepository<SharingBoardVO, Integer>, QuerydslPredicateExecutor<SharingBoardVO> {
	
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QSharingBoardVO board = QSharingBoardVO.sharingBoardVO;
		builder.and(board.shareNum.gt(0)); //and shareNum>0
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
	
	public Page<SharingBoardVO> findByContent(String content, Pageable page);
	
	public Page<SharingBoardVO> findAll(Predicate p, Pageable pageable);
}
