package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.ExerciseTypeRefVO;
import com.kosta.finalProject.models.ExerciseTypeRefVOId;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.services.CenterService;
import com.kosta.finalProject.services.ExerciseTypeRefService;
import com.kosta.finalProject.services.ExerciseTypeService;
import com.kosta.finalProject.services.TrainerService;

@RestController
@RequestMapping("/trainer/*")
public class TrainerController {
		@Autowired
		TrainerService tservice;
		@Autowired
		ExerciseTypeRefService etrservice;
		@Autowired
		ExerciseTypeService etservice;
		@Autowired
		CenterService cservice;
		
		//트레이너 모두 조회
		@GetMapping("/{cnum}")
		public ResponseEntity<List<TrainerVO>> selectAll(@PathVariable int cnum) {
			return new ResponseEntity<>(tservice.selectByCenter(cnum), HttpStatus.OK);
		}
		
		@PostMapping("/insertTrainer/{cnum}/{exerciseTypeNum}")
		public ResponseEntity<List<TrainerVO>> trainerInsertPost(@PathVariable int cnum, @PathVariable int exerciseTypeNum, @RequestBody TrainerVO trainer) {
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
			trainer.setEtyperef(etyperefvo);
			tservice.insertTrainer(trainer);
			
			return new ResponseEntity<>(tservice.selectByCenter(cnum), HttpStatus.OK);
		}
		
		@PutMapping("/updateTrainer/{cnum}/{exerciseTypeNum}/{tnum}")
		public ResponseEntity<List<TrainerVO>> updateTrainer(@PathVariable int cnum, @PathVariable int exerciseTypeNum, @PathVariable int tnum,
				@RequestBody TrainerVO trainer) {
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
			trainer.setEtyperef(etyperefvo);
			trainer.setTrainerNum(tnum);
			tservice.updateTrainer(trainer);
			
			return new ResponseEntity<>(tservice.selectByCenter(cnum), HttpStatus.OK);
		}
		
		@DeleteMapping("/deleteTrainer/{cnum}/{tnum}")
		public ResponseEntity<List<TrainerVO>> deleteTrainer(@PathVariable int cnum, @PathVariable int tnum) {
			tservice.deleteTrainer(tnum);
			
			return new ResponseEntity<>(tservice.selectByCenter(cnum), HttpStatus.OK);
		}
}
