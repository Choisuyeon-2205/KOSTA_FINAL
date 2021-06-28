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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumRegisterVO;
import com.kosta.finalProject.models.CurriculumRegisterVOId;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.ExerciseTypeRefVO;
import com.kosta.finalProject.models.ExerciseTypeRefVOId;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.CenterService;
import com.kosta.finalProject.services.CurriculumRegisterService;
import com.kosta.finalProject.services.CurriculumService;
import com.kosta.finalProject.services.ExerciseTypeRefService;
import com.kosta.finalProject.services.ExerciseTypeService;
import com.kosta.finalProject.services.TrainerService;
import com.kosta.finalProject.services.UserService;

@RestController
public class CurriculumController2 {
	@Autowired
	CurriculumService curservice;
	@Autowired
	CurriculumRegisterService curregservice;
	@Autowired
	ExerciseTypeRefService etrservice;
	@Autowired
	TrainerService tservice;
	@Autowired
	ExerciseTypeService etservice;
	@Autowired
	CenterService cservice;
	@Autowired
	UserService uservice;
	
	//센터에 등록한 사람 정보 모두 조회
	@GetMapping("/curreg/{cnum}")
	public ResponseEntity<List<Object[]>> selectAllByCenterNum(@PathVariable int cnum) {
		return new ResponseEntity<>(curregservice.selectByCenterNum(cnum), HttpStatus.OK);
	}
	
	//센터에 등록한 사람 커리큘럼별 조회 
		@GetMapping("/curreg/getByCurnum/{curnum}")
		public ResponseEntity<List<Object[]>> selectAllByCurNum(@PathVariable int curnum) {
			return new ResponseEntity<>(curregservice.selectByCurriculumNum(curnum), HttpStatus.OK);
		}
	
	//센터 등록정보 삭제
	@DeleteMapping("/curreg/deleteCurreg/{cnum}/{curnum}/{userid}")
	public ResponseEntity<List<Object[]>> deleteTrainer(@PathVariable int cnum, @PathVariable int curnum, @PathVariable String userid) {
		CurriculumRegisterVOId crid= new CurriculumRegisterVOId();
		CurriculumVO curriculum= curservice.selectById(curnum);
		crid.setCurriculum(curriculum);
		UserVO user= uservice.selectById(userid);
		crid.setUser(user);
		int result= curregservice.deleteCurriculumRegister(crid);
		if(result!=0) { //등록성공했을 때만 count
			curriculum.setCurriculumState(curriculum.getCurriculumState()-1);
			curservice.updateCurriculum(curriculum);
		}
		
		return new ResponseEntity<>(curregservice.selectByCenterNum(cnum), HttpStatus.OK);
	}

	@RequestMapping("/center/registerCurriculum/{cnum}")
	public String registerCurriculum(@PathVariable("cnum") int curnum, RedirectAttributes rttr, Authentication authentication) {
		System.out.println(curnum);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
		CurriculumRegisterVOId crid= new CurriculumRegisterVOId();
		CurriculumVO curriculum= curservice.selectById(curnum);
		crid.setCurriculum(curriculum);
		crid.setUser(user);
		
		CurriculumRegisterVO crvo= curregservice.selectById(crid);
		if(crvo!=null) {
			return "이미 등록한 강좌입니다";
		}
		
		crvo= new CurriculumRegisterVO();
		crvo.setId(crid);
		
		
		CurriculumRegisterVO result= curregservice.insertCurriculumRegister(crvo);	
		if(result!=null) { //등록성공했을 때만 count
			curriculum.setCurriculumState(curriculum.getCurriculumState()+1);
			curservice.updateCurriculum(curriculum);
		}
		return result==null?"등록실패":"등록성공";
	}
	

	@GetMapping("/center/curriculumstate/{curnum}")
	public int getState(@PathVariable("curnum") int curriculumnum) {
		return curservice.getState(curriculumnum);
	}
	
	//커리큘럼 모두 조회
	@GetMapping("/curriculum/{cnum}")
	public ResponseEntity<List<CurriculumVO>> selectAll(@PathVariable int cnum) {
		return new ResponseEntity<>(curservice.selectByCenter(cnum), HttpStatus.OK);
	}
			
	@PostMapping("/curriculum/insertCurriculum/{cnum}/{exerciseTypeNum}/{trainerNum}")
	public ResponseEntity<List<CurriculumVO>> CurriculumInsertPost(@PathVariable int cnum, @PathVariable int exerciseTypeNum, @PathVariable int trainerNum , @RequestBody CurriculumVO curriculum) {
		ExerciseTypeRefVOId etyperefvoid= new ExerciseTypeRefVOId();
		CenterVO center= cservice.selectById(cnum);
		ExerciseTypeVO etype= etservice.selectByNum(exerciseTypeNum);
		etyperefvoid.setCenter(center);
		etyperefvoid.setEtype(etype);
				
		//유형찾기...있으면...없으면 insert
		ExerciseTypeRefVO etyperefvo= etrservice.selectByExerciseTypeRefVOId(etyperefvoid);
		if(etyperefvo==null) {
				etyperefvo= ExerciseTypeRefVO.builder().id(etyperefvoid).build();
				etrservice.insertExerciseTypeRef(etyperefvo);			
		}
		curriculum.setEtyperef(etyperefvo);
		
		TrainerVO trainer= tservice.selectById(trainerNum);
		curriculum.setTrainer(trainer);
		
		curservice.insertCurriculum(curriculum);
				
		return new ResponseEntity<>(curservice.selectByCenter(cnum), HttpStatus.OK);
		}
			
	@PutMapping("/curriculum/updateCurriculum/{curnum}/{cnum}/{exerciseTypeNum}/{trainerNum}")
	public ResponseEntity<List<CurriculumVO>> updateTrainer(@PathVariable int curnum, @PathVariable int cnum, @PathVariable int exerciseTypeNum, @PathVariable int trainerNum,
					@RequestBody CurriculumVO curriculum) {
		ExerciseTypeRefVOId etyperefvoid= new ExerciseTypeRefVOId();
		CenterVO center= cservice.selectById(cnum);
		ExerciseTypeVO etype= etservice.selectByNum(exerciseTypeNum);
		etyperefvoid.setCenter(center);
		etyperefvoid.setEtype(etype);
				
		//유형찾기...있으면...없으면 insert
		ExerciseTypeRefVO etyperefvo= etrservice.selectByExerciseTypeRefVOId(etyperefvoid);
		if(etyperefvo==null) {
				etyperefvo= ExerciseTypeRefVO.builder().id(etyperefvoid).build();
				etrservice.insertExerciseTypeRef(etyperefvo);			
		}
		curriculum.setEtyperef(etyperefvo);
		
		TrainerVO trainer= tservice.selectById(trainerNum);
		curriculum.setTrainer(trainer);
		curriculum.setCurriculumNum(curnum);
		
		curservice.updateCurriculum(curriculum);
				
		return new ResponseEntity<>(curservice.selectByCenter(cnum), HttpStatus.OK);
		}
			
	@DeleteMapping("/curriculum/deleteCurriculum/{cnum}/{curnum}")
	public ResponseEntity<List<CurriculumVO>> deleteTrainer(@PathVariable int cnum, @PathVariable int curnum) {
		curservice.deleteCurriculum(curnum);
				
		return new ResponseEntity<>(curservice.selectByCenter(cnum), HttpStatus.OK);
	}
}
