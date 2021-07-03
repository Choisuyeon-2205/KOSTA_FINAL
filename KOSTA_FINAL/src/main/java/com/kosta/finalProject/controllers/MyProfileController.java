package com.kosta.finalProject.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.finalProject.BMI.BMICalculator;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.UserBodyService;
import com.kosta.finalProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class MyProfileController {

	@Autowired
	UserService userservice;
	@Autowired
	UserBodyService ubservice;
	
	
	@GetMapping("/login/profile")
	public void profile() {
	       
	}
	    
	@PostMapping("/login/profile")
	public String profilePost(UserBodyVO body , Principal principal, Authentication authentication, RedirectAttributes rttr) {
		 
	   System.out.println("body:" + body);
	   UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	   UserVO user = userservice.selectById(userDetails.getUsername());
	   
	   UserBodyVO userbody=  ubservice.selectByUserToday(userDetails.getUsername());
	   
	   if(userbody==null) {
		   body.setUser(user);
		   BMICalculator bmi = new BMICalculator();
		   body.setUserBmi(bmi.bmicalculator(body.getWeight(), body.getHeight()));
		   body.setBmiGroup(bmi.getGroup());
		   System.out.println("UserBodyVO : " + body);
	 
		   rttr.addFlashAttribute("body", userservice.updateBMI(body));
		   return "redirect:/body/myprofile";
	   }else {
		   rttr.addFlashAttribute("body", userservice.selectUserBody(user.getUserId())); 
		   rttr.addAttribute("message", "하루에 한번만 등록할 수 있습니다.");
		   return "redirect:/body/myprofile";
	   }
	}
	    
	
	@GetMapping("/body/myprofile")
	    public void myprofile(Model model, UserBodyVO body, Principal principal, Authentication authentication, HttpServletRequest request) {
	       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	       UserVO user = userservice.selectById(userDetails.getUsername());
	       Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
	       UserBodyVO userbody = null;
	       
		       // 그래프 관련 -> 날짜에 따른 체중 변화를 한 번에 제공하기 위해 키:값 형태인 JSONArray를 사용
			   JSONArray jsonArray = new JSONArray();
			
			   List<UserBodyVO> bodylist = userservice.selectGraph(principal.getName());
			   String[] one = new String[bodylist.size()];
	       
	       
	       if(flashMap != null) {
	    	   userbody = (UserBodyVO)flashMap.get("body");
	    	   System.out.println(userbody);
	       }else {
	    	   userbody = userservice.selectUserBody(user.getUserId());
	    	   userbody.setUserBmi(Math.round(userbody.getUserBmi()));
	       }
	       model.addAttribute("user", user);
	       
	       
	       model.addAttribute("body", userbody);
	       System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@" + userbody);
	       
	       System.out.println(user);
	       
		       // 그래프 관련
			   bodylist.forEach(b -> { // 개별 키:값에 대한 for문
					JSONObject jsonObject = new JSONObject();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Timestamp로 설정한 탓에 불필요한 시간 정보까지 모두 출력됨 -> 연월일로 제한
					jsonObject.put("weight", b.getWeight());
					jsonObject.put("insertDate", sdf.format(b.getInsertDate()));
					jsonArray.add(jsonObject);
					});
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("data1", jsonArray); // 개별 값을 배열 형태로 집어넣음
						 
				model.addAttribute("bodylist", jsonObject2); // 리스트 형태, 즉 유저별 체중 변화 정보
	    }
	    
}
