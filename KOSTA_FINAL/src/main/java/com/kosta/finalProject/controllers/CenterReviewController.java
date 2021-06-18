package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.finalProject.models.CenterReviewVO;
import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.CenterReviewService;
import com.kosta.finalProject.services.CurriculumRegisterService;
import com.kosta.finalProject.services.CurriculumService;

@RestController
@RequestMapping("/reviews/*") //reviews로 시작하는 것은 전부 받겠다.
public class CenterReviewController {

	@Autowired
	CenterReviewService crservice;
	@Autowired
	CurriculumRegisterService curregservice;
	@Autowired
	CurriculumService cservice;
	
		//댓글 상세보기 selectById
		@GetMapping("/{rno}")
		public ResponseEntity<CenterReviewVO> viewReply(@PathVariable int rno) {
			return new ResponseEntity<>(crservice.selectById(rno), HttpStatus.OK);
			
		}

		//게시글의 모든 댓글 조회
		@GetMapping("/center/{cno}")
		public ResponseEntity<List<CenterReviewVO>> selectAll(@PathVariable int cno) {
	
			return new ResponseEntity<>(crservice.selectByCenter(cno), HttpStatus.OK);
		}
		
		//평점 평균 조회 get
		@GetMapping("/prefer/{cno}")
		public double getReviewPreference(@PathVariable int cno) {
			return crservice.calAvgPreference(cno);
		}
		
		//특정 board의 댓글 입력=> 재조회
		@PostMapping("/{cno}/{curnum}")
		public ResponseEntity<List<CenterReviewVO>> addReview(@PathVariable int cno,@PathVariable int curnum,
				@RequestBody CenterReviewVO review, Authentication authentication) {
			CenterVO center= CenterVO.builder().centerNum(cno).build();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
			UserVO user = UserVO.builder()
					.userId(userDetails.getUsername())				
					.build();	
			
			CurriculumVO curriculum= cservice.selectById(curnum);
			review.setCurriculum(curriculum);
			review.setUser(user);
			review.setCenter(center);
			crservice.updateOrInsert(review);
			
			return new ResponseEntity<>(crservice.selectByCenter(cno), HttpStatus.CREATED);
		}
		
		@PutMapping("/{cno}/{crnum}/{curnum}")
		public ResponseEntity<List<CenterReviewVO>> updateReview(@PathVariable int cno, @PathVariable int crnum,@PathVariable int curnum,
				@RequestBody CenterReviewVO review, Authentication authentication) {
			
			CenterVO center= CenterVO.builder().centerNum(cno).build();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
			UserVO user = UserVO.builder()
					.userId(userDetails.getUsername())				
					.build();
			
			CurriculumVO curriculum= cservice.selectById(curnum);
			review.setCurriculum(curriculum);
			review.setUser(user);
			review.setCenter(center);
			crservice.updateOrInsert(review);
			
			return new ResponseEntity<>(crservice.selectByCenter(cno), HttpStatus.OK);
		}
		
		@DeleteMapping("/{cno}/{rno}")
		public ResponseEntity<List<CenterReviewVO>> deleteByRno(@PathVariable int cno,
				@PathVariable int rno) {
			crservice.deleteReview(rno);
			
			CenterVO center= CenterVO.builder().centerNum(cno).build();
			return new ResponseEntity<>(crservice.selectByCenter(cno), HttpStatus.OK);
		}
}
