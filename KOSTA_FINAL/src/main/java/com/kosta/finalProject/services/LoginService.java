package com.kosta.finalProject.services;


 
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.login.SecurityUser;
import com.kosta.finalProject.models.UserAddress;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.LoginRepository;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Service
public class LoginService implements UserDetailsService {
     @Autowired
      private LoginRepository repo;
        
      @Autowired
      private PasswordEncoder passwordEncoder;

  
      @Override
      public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { 
         System.out.println(userId + "요기");
          UserDetails user = repo.findById(userId)
                .filter(u ->u!=null).map(u->new SecurityUser(u)).get();
             return user;
      }
      // 이름 넣어서, 
      public void signup(UserVO user, UserAddress userAddress) {
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         user.setUserPw(passwordEncoder.encode(user.getUserPw()));
         System.out.println(user);
         System.out.println(userAddress);
        UserVO newUser = UserVO.builder()
       		 .userId(user.getUserId())
             .userName(user.getUserName())
             .nickName(user.getNickName())
             .userAddress(userAddress) //여기에 넣기
             .userEmail(user.getUserEmail())
             .userPhone(user.getUserPhone())
             .userPhoto(user.getUserPhoto())
             .userPw(user.getUserPw()).build();
        repo.save(newUser);		
      }
      
      public void certifiedPhoneNumber(String user_phone, String cerNum) {

           String api_key = "NCS6FQ4VTRUTGQQT";
           String api_secret = "7PJ87LGTUYGTHHIIXUW1V119FZN590EF";
           Message coolsms = new Message(api_key, api_secret);

           // 4 params(to, from, type, text) are mandatory. must be filled
           HashMap<String, String> params = new HashMap<String, String>();
           params.put("to", user_phone);    // 수신전화번호
           params.put("from", "01033680126");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
           params.put("type", "SMS");
           params.put("text", " 휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
           params.put("app_version", "test app 1.2"); // application name and version

           try {
               JSONObject obj = (JSONObject) coolsms.send(params);
               System.out.println("**********" + obj.toString());
           } catch (CoolsmsException e) {
               System.out.println(e.getMessage());
               System.out.println(e.getCode());
           }

       } 
      public boolean checkName(String userId) {
    	  return repo.findById(userId).isPresent();
      }
    
}