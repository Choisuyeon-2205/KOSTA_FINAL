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
	public void profile(HttpServletRequest request, Model model) {
		Map<String, ?> flashMap =RequestContextUtils.getInputFlashMap(request);
		String message= null;
        if(flashMap!=null) {
        	message =(String)flashMap.get("message");
       }   
       model.addAttribute("message",message);
	}
	    
	@PostMapping("/login/profile")
	public String profilePost(UserBodyVO body , Principal principal, Authentication authentication, RedirectAttributes rttr) {
		 
	   UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	   UserVO user = userservice.selectById(userDetails.getUsername());
	   UserBodyVO userbody=  ubservice.selectByUserToday(userDetails.getUsername());
	   
	   if(userbody==null) {
		   body.setUser(user);
		   BMICalculator bmi = new BMICalculator();
		   body.setUserBmi(bmi.bmicalculator(body.getWeight(), body.getHeight()));
		   body.setBmiGroup(bmi.getGroup());
	 
		   rttr.addFlashAttribute("body", userservice.updateBMI(body));
		   rttr.addFlashAttribute("message", "입력 성공");
		   return "redirect:/body/myprofile";
	   }else {
		   rttr.addFlashAttribute("body", userservice.selectUserBody(user.getUserId())); 
		   rttr.addFlashAttribute("message", "하루에 한번만 등록할 수 있습니다.");
		   return "redirect:/body/myprofile";
	   }
	}
	    
	
	@GetMapping("/body/myprofile")
	    public void myprofile(Model model, UserBodyVO body, Principal principal, Authentication authentication, HttpServletRequest request) {
	       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	       UserVO user = userservice.selectById(userDetails.getUsername());
	       Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
	       UserBodyVO userbody = null;
	       
	       // 그래프 관련
		   JSONArray jsonArray = new JSONArray();
		   List<UserBodyVO> bodylist = userservice.selectGraph(principal.getName());
	       
	       if(flashMap != null) {
	    	   userbody = (UserBodyVO)flashMap.get("body");
	       }else {
	    	   userbody = userservice.selectUserBody(user.getUserId());
	    	   if(userbody!=null)
	    		   userbody.setUserBmi(Math.round(userbody.getUserBmi()));
	       }
	       model.addAttribute("user", user);
	       model.addAttribute("body", userbody);
	       
	       // 그래프 관련
		   bodylist.forEach(b -> { 
				JSONObject jsonObject = new JSONObject();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				jsonObject.put("weight", b.getWeight());
				jsonObject.put("insertDate", sdf.format(b.getInsertDate()));
				jsonArray.add(jsonObject);
				});
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("data1", jsonArray);
					 
			model.addAttribute("bodylist", jsonObject2);
		       
	       
	    }
	    
}