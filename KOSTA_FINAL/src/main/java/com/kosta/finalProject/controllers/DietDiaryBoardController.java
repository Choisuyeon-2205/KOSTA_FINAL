package com.kosta.finalProject.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.DietDiaryBoardService;
import com.kosta.finalProject.services.UserService;

@Controller
public class DietDiaryBoardController {

	@Autowired
	DietDiaryBoardService service;
	
	@Autowired
	UserService uservice;
	

	@GetMapping("/dietdiaryboard/boardlist")
	public void selectAll(Model model, HttpServletRequest request) {
		model.addAttribute("dboardlist", service.selectAll());
		
	}

	
	
	@GetMapping("/dietdiaryboard/boarddetail")
	public void selectById(Model model, Integer diaryNum, Principal principal, Authentication authentication ) {
		//System.out.println(service.selectById(diaryNum));
		model.addAttribute("dboard", service.selectById(diaryNum));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user =  uservice.selectById(userDetails.getUsername());
				
				 
	      model.addAttribute("user",user);
	      //System.out.println("******************************" + user);
		  //System.out.println("로그인한사람:" + userDetails);
	}

	@GetMapping("/dietdiaryboard/register")
	public void boardRegister() {
		
	}
	
	@PostMapping("/dietdiaryboard/register")
	public String boardRegisterPost(DietDiaryBoardVO board, RedirectAttributes rttr, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = UserVO.builder()
				.userId(userDetails.getUsername())
				.build();
		board.setUser(user); 
		DietDiaryBoardVO ins_board = service.insertBoard(board);
		
		rttr.addFlashAttribute("resultMessage", ins_board==null?"입력실패":"입력성공");
		return "redirect:/dietdiaryboard/boardlist";
	}

	@GetMapping("/dietdiaryboard/delete")
	public String boardDelete(Integer diaryNum, RedirectAttributes rttr ) {
		int ret = service.deleteBoard(diaryNum);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/dietdiaryboard/boardlist";
	}
	
	@PostMapping("/dietdiaryboard/update")
	public String boardUpdate(DietDiaryBoardVO board, String userId, RedirectAttributes rttr, Authentication authentication) {
		//System.out.println("업데이트###############" + userId);
		//System.out.println("업데이트###############" + board);
		board.setUser(uservice.selectById(userId));
		DietDiaryBoardVO update_board = service.updateBoard(board);
		
		rttr.addFlashAttribute("resultMessage", update_board==null?"수정실패":"수정성공");
		return "redirect:/dietdiaryboard/boardlist";
	}





















}
