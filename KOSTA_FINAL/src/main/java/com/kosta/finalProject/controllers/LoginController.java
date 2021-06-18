package com.kosta.finalProject.controllers;




import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kosta.finalProject.models.UserAddress;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login/*")
public class LoginController {

   @Autowired
   LoginService loginservice;

    @GetMapping(value = "/login")
    public void login() {
    	
       // HttpServletRequest req;
       // String referer = req.getHeader("Referer"); // 현재 요청된 페이지의 이전 페이지 주소 정보를 포함
        // req.getSession().setAttribute("prevPage", referer);
       // return "login";
    }

    @RequestMapping("/hello")   
    public void hello() {
   
    }
    @PostMapping(value = "/login")
    public String logins() {
        return "/login/hello";
    }
    
    @RequestMapping("/login/logout")
    public String logout() {
 
       return "/login/login";
    }
    @RequestMapping("/accessDenied")
    public void accessDenied() {
 
    }
    
    @PostMapping("/signUp")
    public String signup(UserVO user, String userAddress1, String userAddress2, String userAddress3, String AddNum) { // 회원 추가
    	System.out.println("PostMapping 도착");
    	UserAddress userAddress = new UserAddress();
    	userAddress.setAddNum(AddNum);
    	userAddress.setUserAddress1(userAddress1);
    	userAddress.setUserAddress2(userAddress2);
    	userAddress.setUserAddress3(userAddress3);
       loginservice.signup(user, userAddress);
      return "redirect:/login/hello";
    }
    @GetMapping("/signup")
    public void signupget() { // 회원 추가
      
    }
    
    public UserVO getUser() { //
        UserVO user = new UserVO();
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) user = (UserVO) auth.getPrincipal();
        return user;
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    @RequestMapping("/testtest")
    @ResponseBody
    public String SMSController(String user_phone) {
       System.out.println("***********USER PHONE************* " + user_phone);
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        System.out.println("수신자 번호 : " + user_phone);
        System.out.println("인증번호 : " + numStr);
        loginservice.certifiedPhoneNumber(user_phone,numStr);
        return numStr;
    }
   
   @ResponseBody
   @GetMapping("/duCheck")
   public int checkId(String userId){
	   return loginservice.checkName(userId)? 0 : 1;
   }

    
}