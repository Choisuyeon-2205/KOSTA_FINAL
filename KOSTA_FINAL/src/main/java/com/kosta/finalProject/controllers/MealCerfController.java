package com.kosta.finalProject.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.finalProject.models.MealCerfVO;
import com.kosta.finalProject.models.MealId;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.MealService;
import com.kosta.finalProject.services.UserService;

@Controller
@RequestMapping("/meal/*")
public class MealCerfController {
	@Autowired
	MealService service;
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
		} else {
			Date newDate = Date.valueOf(startDate);
			cal.setTime(newDate);
			lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
		
		List<MealCerfVO> result = service.selectAllWithPredicate(user, startDate);
		model.addAttribute("startDate", startDate);
		model.addAttribute("mealRecord", result);
		model.addAttribute("lastDay", lastDay);
		return "mealCerf/record_slide";
	}
	
	@PostMapping("/insertRecord")
	public String uploadPost(Principal principal, Authentication authentication, MealCerfVO meal) throws IOException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(cal.getTime());
		MealId mid = new MealId();
		mid.setMealDate(Date.valueOf(today));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
		mid.setUser(user);
		meal.setMealId(mid);;
		service.insertMealCerf(meal);
		return "redirect:/meal/records";
	}
	
	@GetMapping("/deleteRecord")
	public String deletePost(Principal principal, Authentication authentication, String meal_type) {
		MealId mid = new MealId();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(cal.getTime());
		mid.setMealDate(Date.valueOf(today));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
		mid.setUser(user);
		MealCerfVO meal = service.selectById(mid);
		if (meal_type.equals("breakfast")) {
			meal.setBreakfastImage("");
		} else if (meal_type.equals("lunch")) {
			meal.setLunchImage("");
		} else {
			meal.setDinnerImage("");
		}
		service.insertMealCerf(meal);
		return "redirect:/meal/records";
	}

}
