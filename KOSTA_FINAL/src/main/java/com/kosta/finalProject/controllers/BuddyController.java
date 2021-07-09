package com.kosta.finalProject.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
			UserBodyVO buddybodyInfo= ubservice.selectByUser(user2.getUserId());
			buddybodyInfo.setUserBmi(Math.round(buddybodyInfo.getUserBmi()));
			model.addAttribute("buddybodyInfo",buddybodyInfo);
			model.addAttribute("myInfo",user);
			UserBodyVO mybodyInfo= ubservice.selectByUser(user.getUserId());
			mybodyInfo.setUserBmi(Math.round(mybodyInfo.getUserBmi()));
			model.addAttribute("mybodyInfo",mybodyInfo);
			// 그래프 관련 1(buddy)
			JSONArray jsonArray = new JSONArray();
			
			List<UserBodyVO> buddybodylist = uservice.selectGraph(user2.getUserId());
			
			buddybodylist.forEach(b -> { 
					JSONObject jsonObject = new JSONObject();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					jsonObject.put("weight", b.getWeight());
					jsonObject.put("insertDate", sdf.format(b.getInsertDate()));
					jsonArray.add(jsonObject);
					});
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("data1", jsonArray);					 
			model.addAttribute("buddybodylist", jsonObject2);
			
			// 그래프 관련	2(user)
			JSONArray jsonArray2 = new JSONArray();
			List<UserBodyVO> mybodylist = uservice.selectGraph(user.getUserId());
			
			mybodylist.forEach(b -> { 
					JSONObject jsonObject = new JSONObject();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					jsonObject.put("weight", b.getWeight());
					jsonObject.put("insertDate", sdf.format(b.getInsertDate()));
					jsonArray2.add(jsonObject);
					});
			JSONObject jsonObject3 = new JSONObject();
			jsonObject3.put("data1", jsonArray2);
						 
			model.addAttribute("mybodylist", jsonObject3);
			
			return "buddy/buddyprofile";
		}else {
			
			return "buddy/newbuddy";
		}
	}
	
	@RequestMapping("/buddy/searchbuddy")
	public String searchNewBuddy(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
		
		UserBodyVO mybody= ubservice.selectByUser(user.getUserId());
		//내 body 정보가 없으면 매칭 불가능!
		if(mybody==null) {
			model.addAttribute("message","바디프로필 정보를 먼저 입력하세요.");
			return "buddy/buddyfail";
		}
	
		List<UserBodyVO> buddys= ubservice.findIsBuddy(user.getUserId(), mybody.getBmiGroup());
		//매칭할 list가 없으면 실시간 매칭 불가능 !
		if(buddys.size()==0) {
			UserBodyVO userbody= ubservice.selectByUser(user.getUserId());
			userbody.setBuddyCheck(userbody.getBuddyCheck()+1);
			ubservice.updateUserBody(userbody);
			model.addAttribute("message","매칭할 사람을 찾지 못하였습니다.");
			return "buddy/buddyfail";
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
		bservice.insertOrUpdateBuddy(buddy);
		
		UserBodyVO userbody= ubservice.selectByUser(buddys.get(0).getUser().getUserId());
		userbody.setBuddyCheck(userbody.getBuddyCheck()-1);
		ubservice.updateUserBody(userbody);
		
		return "redirect:/buddy/newbuddy";	
	}

}
