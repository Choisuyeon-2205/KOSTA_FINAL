package com.kosta.finalProject;

import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.DietDiaryReplyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.DietDiaryBoardRepository;

import lombok.extern.java.Log;
@Commit
@Log
@SpringBootTest
public class DietDiaryBoardTest {

	@Autowired
	DietDiaryBoardRepository repo;
	
	@Test // 보드입력하기
	public void insertBoard() {
		IntStream.range(10, 170).forEach(i->{
			UserVO user = new UserVO();
			user.setUserId("sample1");
			DietDiaryBoardVO board = DietDiaryBoardVO.builder()
					.title("제목" + i)
					.content("내용" + i)
					.photo("사진" + i)
					.user(user)
					.build();
			repo.save(board);
		});
	}
	
	
	//@Transactional
	//@Test
	 public void insertReply() {
		repo.findById(1).ifPresent(b->{
			List<DietDiaryReplyVO> replies = b.getDreplies();
			IntStream.range(1, 5).forEach(i->{
			UserVO user = new UserVO();
			//DietDiaryBoardVO dboard = new DietDiaryBoardVO();
			user.setUserId("test" + i);
			//dboard.setDiaryNum(1);
			DietDiaryReplyVO dreply = DietDiaryReplyVO.builder()
					.user(user)
					.dboard(b)
					.content("테스트" + i)
					.build();
			replies.add(dreply);
			});
			repo.save(b);
		});
	} 
	
	
	

	
	
	
	
	
	
	
	
	
}
