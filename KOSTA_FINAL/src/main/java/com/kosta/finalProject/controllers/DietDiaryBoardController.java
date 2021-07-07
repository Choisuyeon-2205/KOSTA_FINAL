package com.kosta.finalProject.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.finalProject.models.DietDiaryBoardVO;
import com.kosta.finalProject.models.PageMaker;
import com.kosta.finalProject.models.PageVO;
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
	public void selectAll(Model model, HttpServletRequest request, PageVO pagevo) {
		model.addAttribute("dboardlist", service.selectAll());

		Page<DietDiaryBoardVO> result = service.selectAll(pagevo);

		model.addAttribute("boardResult", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMaker<>(result));
	}

	
	
	@GetMapping("/dietdiaryboard/boarddetail")
	public void selectById(Model model, Integer diaryNum, Principal principal, Authentication authentication, PageVO pagevo ) {
		model.addAttribute("dboard", service.selectById(diaryNum));
		model.addAttribute("pagevo", pagevo);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user =  uservice.selectById(userDetails.getUsername());
		model.addAttribute("user",user);

	}

	@GetMapping("/dietdiaryboard/register")
	public void boardRegister() {
		
	}
	
	@PostMapping("/dietdiaryboard/register")
	public String boardRegisterPost(DietDiaryBoardVO board, RedirectAttributes rttr, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserVO user = uservice.selectById(userDetails.getUsername());
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
	public String boardUpdate(DietDiaryBoardVO board, String userId, RedirectAttributes rttr, Authentication authentication, Integer page, Integer size, String type, String keyword) {
		board.setUser(uservice.selectById(userId));
		DietDiaryBoardVO update_board = service.updateBoard(board);	
		rttr.addFlashAttribute("resultMessage", update_board==null?"수정실패":"수정성공");
		
		//방법1...주소창에 안보이기 
		PageVO pagevo = PageVO.builder()
						.page(page).size(size).type(type).keyword(keyword)
						.build();
		rttr.addFlashAttribute("pagevo", pagevo);
		//방법2...주소창에 보이기 
		String param = "page=" + page + "&size=" + size + "&type="+type + "&keyword=" + keyword;
		return "redirect:/dietdiaryboard/boardlist?" + param;
			
	}

}
