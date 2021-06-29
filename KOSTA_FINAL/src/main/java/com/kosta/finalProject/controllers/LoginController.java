package com.kosta.finalProject.controllers;




import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kosta.finalProject.BMI.BMICalculator;
import com.kosta.finalProject.models.BusinessAddress;
import com.kosta.finalProject.models.BusinessVO;
import com.kosta.finalProject.models.UserAddress;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserRoleEnumType;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.services.LoginService;
import com.kosta.finalProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping /* ("/login/*") */
public class LoginController {

   @Autowired
   LoginService loginservice;
   
   @Autowired
   UserService userservice;
   
   private final HttpSession httpSession; // 로그인 여부를 확인하기 위한 세션

    @GetMapping(value = "/main")
    public void login() {
       System.out.println("login get.....");
    }

    @RequestMapping("/main")   
    public void main() {
   
    }
    
    @PostMapping(value = "/login/login")
    public String logins() {
       System.out.println("login PostMapping");
        return "/main";
    }

    
    @RequestMapping("/login/logout")
    public String logout() {
 System.out.println("로그아웃처리..");
       return "redirect:/main";
    }
    @RequestMapping("/login/accessDenied")
    public void accessDenied() {
    	
    }
    
 
    
    @PostMapping( value = "/login/signUp")
    public String signup(UserVO user, String userAddress1, String userAddress2, String userAddress3, String AddNum ) { // 회원 추가
       System.out.println("PostMapping 도착");
       UserAddress userAddress = new UserAddress();
       user.setUrole(UserRoleEnumType.USER);
       userAddress.setAddNum(AddNum);
       userAddress.setUserAddress1(userAddress1);
       userAddress.setUserAddress2(userAddress2);
       userAddress.setUserAddress3(userAddress3);
      
       loginservice.signup(user, userAddress); 
  
      return "redirect:/main";
    }
    @GetMapping("/login/signup")
    public void signupget() { // 회원 추가
      
    }
    @GetMapping("/login/profile")
    public void profile() {
       
    }
    
    @PostMapping("/login/profile")
    public String profilePost(UserBodyVO body, Principal principal,Authentication authentication) {
       UserVO user = new UserVO();
       user.setUserId(principal.getName());
    body.setUser(user);
       BMICalculator bmi = new BMICalculator();
       body.setUserBmi(bmi.bmicalculator(body.getWeight(), body.getHeight()));
       System.out.println("UserBodyVO : " + body);
       userservice.updateBMI(body);
       return "redirect:/main";
    }
    
    @GetMapping("/login/myprofile")
    public void myprofile() {
       
    }
    
    
    
    @GetMapping("/login/BusinessSignup")
    public void BusinessSignup() { // 회원 추가
      
    }
    @PostMapping("/login/BusinessSignUp")
    public String BusinessSignup2(BusinessVO business, String Address1, String Address2, String Address3, String AddNum) { // 회원 추가
       System.out.println("PostMapping 도착");
       BusinessAddress  businessAddress= new BusinessAddress();
       business.setBrole(UserRoleEnumType.BUSINESS);
       businessAddress.setAddNum(AddNum);
       businessAddress.setAddress1(Address1);
       businessAddress.setAddress2(Address2);
       businessAddress.setAddress3(Address3);
       
       business.setBusinessAddress(businessAddress);
       System.out.println("business 만들기:" + business);
       loginservice.businessSingup(business, businessAddress);
      return "redirect:/main";
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
    
    @RequestMapping("/login/testtest")
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
    
    @RequestMapping("/login/businessPhone")
    @ResponseBody
    public String SMSController2(String businessPhone) {
       System.out.println("***********USER PHONE************* " + businessPhone);
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        System.out.println("수신자 번호 : " + businessPhone);
        System.out.println("인증번호 : " + numStr);
        loginservice.certifiedPhoneNumber2(businessPhone,numStr);
        return numStr;
    }
   
   @ResponseBody
   @GetMapping("/login/duCheck")
   public int checkId(String userId){
      return loginservice.checkName(userId)? 0 : 1;
   }

   @ResponseBody
   @GetMapping("/login/nickCheck")
   public int CheckNickName(String nickName) {
      return loginservice.checkNickName(nickName)? 0: 1;
   }
    
   @ResponseBody
   @GetMapping("/login/businessCheck")
   public int businessCheck(String businessId){
      return loginservice.businessId(businessId)? 0 : 1;
   }
}