package com.kosta.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
	@GetMapping("/center/googleMap")
	public void selectMap(Model model, String address) {
		model.addAttribute("address", address);
	}
}
