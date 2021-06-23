package com.kosta.finalProject.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.services.UserService;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class GraphController {

	private static final Logger logger = LoggerFactory.getLogger(UserBodyVO.class);
	
	@Setter
	private UserService service;
	
	@RequestMapping(value = "dataBody", method = RequestMethod.GET)
	public String dataBody(Locale locale, Model model) {
		return "dataBody";
	}
	

//	@RequestMapping(value = "bodyList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
//	public @RequestBody String bodyList(Locale locale, Model model) {
//		Gson gson = new Gson();
//		List<UserBodyVO> list = service.getClass();
//		
//		return gson.toJson(list);
//	}
	
}
