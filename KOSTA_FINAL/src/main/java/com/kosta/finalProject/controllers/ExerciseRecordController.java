package com.kosta.finalProject.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.finalProject.models.ExerciseRecordId;
import com.kosta.finalProject.models.ExerciseRecordVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.ExerciseRecordService;
import com.kosta.finalProject.services.UserService;

@Controller
@RequestMapping("/exercise/*")
public class ExerciseRecordController {
	@Autowired
	ExerciseRecordService service;
	@Autowired
	UserService userservice;
	
	@GetMapping("/records")
	public String cardTest(String startDate, Model model, Principal principal, Authentication authentication) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		int lastDay;
		if (startDate == null) {
			lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else if (startDate.equals(cal.toString())) {
			startDate = null;
			lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		else {
			Date newDate = Date.valueOf(startDate);
			cal.setTime(newDate);
			lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
		List<ExerciseRecordVO> result = service.selectAllWithPredicate(user, startDate);
		model.addAttribute("startDate", startDate);
		model.addAttribute("exRecord", result);
		model.addAttribute("lastDay", lastDay);
		return "exerciseRecord/record_slide";
	}
	
	@PostMapping("/insertRecord")
	public String uploadPost(Principal principal, Authentication authentication, ExerciseRecordVO exercise) throws IOException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(cal.getTime());
		ExerciseRecordId eid = new ExerciseRecordId();
		eid.setExerciseDate(Date.valueOf(today));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
		eid.setUser(user);
		exercise.setExerciseId(eid);
		service.insertExerciseRecord(exercise);
		return "redirect:/exercise/records";
	}
	
	@GetMapping("/deleteRecord")
	public String deletePost(Principal principal, Authentication authentication, String delDate) {
		ExerciseRecordId eid = new ExerciseRecordId();
		eid.setExerciseDate(Date.valueOf(delDate));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
		eid.setUser(user);
		service.deleteExerciseRecord(eid);
		return "redirect:/exercise/records";
	}

}
