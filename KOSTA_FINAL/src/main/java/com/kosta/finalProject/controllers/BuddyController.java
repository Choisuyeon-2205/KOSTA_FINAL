package com.kosta.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuddyController {
	
	
	@GetMapping("/apply")
	public String apply(Model model) {
		return "";
	}

}
