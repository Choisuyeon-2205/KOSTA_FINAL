package com.kosta.finalProject.controllers;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.kosta.finalProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class MyProfileController {

	@Autowired
	UserService userservice;
	
	
	@GetMapping("/login/profile")
	public void profile() {
	       
	}
	    
	@PostMapping("/login/profile")
	public String profilePost(UserBodyVO body, Principal principal,Authentication authentication, RedirectAttributes rttr) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = userservice.selectById(userDetails.getUsername());
	   body.setUser(user);
	   BMICalculator bmi = new BMICalculator();
	   body.setUserBmi(bmi.bmicalculator(body.getWeight(), body.getHeight()));
	   System.out.println("UserBodyVO : " + body);
 
	   rttr.addFlashAttribute("body", userservice.updateBMI(body));
	   return "redirect:/body/myprofile";
	}
	    
	
	@GetMapping("/body/myprofile")
	    public void myprofile(Model model, Principal principal, Authentication authentication, HttpServletRequest request) {
	       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	       UserVO user = userservice.selectById(userDetails.getUsername());
	       Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
	       UserBodyVO userbody = null;
	       if(flashMap != null) {
	    	   userbody = (UserBodyVO)flashMap.get("body");
	    	   System.out.println(userbody);
	       }else {
	    	   userbody = userservice.selectUserBody(user.getUserId());
	       }
	       model.addAttribute("user", user);
	       
	       
	       model.addAttribute("body", userbody);
	       System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@" + userbody);
	       
	       System.out.println(user);
	       
	       
	    }
	    
	@GetMapping("/body/bmi")
	public void bmiGraph() {
		
	}
}
