package com.kosta.finalProject.controllers;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.finalProject.models.BuddyVO;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.BuddyService;
import com.kosta.finalProject.services.UserBodyService;
import com.kosta.finalProject.services.UserService;

@Controller
public class BuddyController {
	@Autowired
	UserService uservice;
	@Autowired
	UserBodyService ubservice;
	@Autowired
	BuddyService bservice;
	
	// 버디 없을 때 main
	@GetMapping("/buddy/newbuddy")
	public String getNewBuddy(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
		
		BuddyVO buddy= bservice.selectByUserId(userDetails.getUsername());
		if(buddy!=null) {
			UserVO user2= buddy.getUser1();
			if(user2==user) {
				user2= buddy.getUser2();
			}
			model.addAttribute("buddyInfo",user2);
			model.addAttribute("buddybodyInfo",ubservice.selectByUser(user2.getUserId()));
			model.addAttribute("myInfo",user);
			model.addAttribute("mybodyInfo",ubservice.selectByUser(user.getUserId()));
			return "/buddy/buddyprofile";
		}else {
			
			return "/buddy/newbuddy";
		}
	}
	
	@RequestMapping("/buddy/searchbuddy")
	public String searchNewBuddy(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
		
		UserBodyVO mybody= ubservice.selectByUser(user.getUserId());
		System.out.println("userid= "+user.getUserId()+", myBMI: "+mybody.getBmiGroup());
		List<UserBodyVO> buddys= ubservice.findIsBuddy(user.getUserId(), mybody.getBmiGroup());
		System.out.println(buddys);
		System.out.println(buddys.size());
		//매칭할 list가 없으면 실시간 매칭 불가능 !
		if(buddys.size()==0) {
			UserBodyVO userbody= ubservice.selectByUser(user.getUserId());
			userbody.setBuddyCheck(userbody.getBuddyCheck()+1);
			ubservice.updateUserBody(userbody);
			return "/buddy/buddyfail";
		}
		
		Collections.shuffle(buddys); // 랜덤 재배치
		Date date= new Date();
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		BuddyVO buddy= BuddyVO.builder()
				.user1(user)
				.user2(buddys.get(0).getUser())
				.startDate(date)
				.endDate(cal.getTime())
				.build();
		bservice.insertOrUpdateBuddy(buddy); //이까지만 됌
		
		UserBodyVO userbody= ubservice.selectByUser(buddys.get(0).getUser().getUserId());
		userbody.setBuddyCheck(userbody.getBuddyCheck()-1);
		ubservice.updateUserBody(userbody);
		
		return "/buddy/buddyprofile";	
	}

}
