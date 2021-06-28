package com.kosta.finalProject.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.finalProject.models.HealthBoardVO;
import com.kosta.finalProject.models.PageMaker;
import com.kosta.finalProject.models.PageVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.HealthBoardService;
import com.kosta.finalProject.services.UserService;

@Controller
public class HealthBoardController {

	@Autowired
	HealthBoardService service;
	
	@Autowired
	UserService uservice;
	
	@GetMapping("/healthboard/boardlist")
	public void selectAll(Model model, HttpServletRequest request, PageVO pagevo) {
		model.addAttribute("hboardlist", service.selectAll());
		Page<HealthBoardVO> result = service.selectAll(pagevo);
		model.addAttribute("boardResult", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMaker<>(result));
	}
	
	@GetMapping("/healthboard/boarddetail")
	public void selectById(Model model, Integer healthNum, Principal principal, Authentication authentication,
			PageVO pagevo) {
		model.addAttribute("hboard", service.selectById(healthNum));
		model.addAttribute("pagevo", pagevo);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
		model.addAttribute("user", user);
	}
	
	@GetMapping("/healthboard/register")
	public void boardRegister() {

	}
	
	@PostMapping("/healthboard/register")
	public String boardRegisterPost(HealthBoardVO board, RedirectAttributes rttr, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//UserVO user = UserVO.builder().userId(userDetails.getUsername()).build();
		UserVO user = uservice.selectById(userDetails.getUsername());
		board.setUser(user);
		HealthBoardVO ins_board = service.insertBoard(board);

		rttr.addFlashAttribute("resultMessage", ins_board == null ? "입력실패" : "입력성공");
		return "redirect:/healthboard/boardlist";
	}
	
	@GetMapping("/healthboard/delete")
	public String boardDelete(Integer healthNum, RedirectAttributes rttr) {
		int ret = service.deleteBoard(healthNum);
		rttr.addFlashAttribute("resultMessage", ret == 0 ? "삭제실패" : "삭제성공");
		return "redirect:/healthboard/boardlist";
	}

	@PostMapping("/healthboard/update")
	public String boardUpdate(HealthBoardVO board, String userId, RedirectAttributes rttr,
			Authentication authentication, Integer page, Integer size, String type, String keyword) {
		board.setUser(uservice.selectById(userId));
		HealthBoardVO update_board = service.updateBoard(board);

		rttr.addFlashAttribute("resultMessage", update_board == null ? "수정실패" : "수정성공");

		// 방법1...주소창에 안보이기
		PageVO pagevo = PageVO.builder().page(page).size(size).type(type).keyword(keyword).build();
		rttr.addFlashAttribute("pagevo", pagevo);
		// 방법2...주소창에 보이기
		String param = "page=" + page + "&size=" + size + "&type=" + type + "&keyword=" + keyword;
		return "redirect:/healthboard/boardlist?" + param;

	}
	
}
