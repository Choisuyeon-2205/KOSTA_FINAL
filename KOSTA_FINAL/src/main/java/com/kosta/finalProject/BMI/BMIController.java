package com.kosta.finalProject.BMI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;

@Controller
public class BMIController {

	@Autowired
	BMICalculator bmi;
	
	@Autowired
	UserVO uvo;
	
	@Autowired
	UserBodyVO ubody;
	
	@GetMapping("/입력할 화면 갈 예정")
	public void bodyInfoInsert() {
		
	}
	@PostMapping("/등록할 ~")
	public String bodyInfoPost(UserBodyVO userbody) {
		userbody.setUserBmi(bmi.bmicalculator(userbody.getWeight(), userbody.getHeight()));
		
		
		
		return null;	
	}
}
