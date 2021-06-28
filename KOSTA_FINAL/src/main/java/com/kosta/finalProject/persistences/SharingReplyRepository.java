package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.SharingBoardVO;
import com.kosta.finalProject.models.SharingReplyVO;

public interface SharingReplyRepository extends CrudRepository<SharingReplyVO, Integer> {
	
	public List<SharingReplyVO> findBySboard(SharingBoardVO sboard);
}
