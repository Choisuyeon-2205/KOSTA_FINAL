package com.kosta.finalProject.services;


 
import java.util.HashMap;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.login.SecurityBusiness;
import com.kosta.finalProject.login.SecurityUser;
import com.kosta.finalProject.models.BusinessAddress;
import com.kosta.finalProject.models.BusinessVO;
import com.kosta.finalProject.models.UserAddress;
import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserRoleEnumType;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.BusinessRepository;
import com.kosta.finalProject.persistences.LoginRepository;
import com.kosta.finalProject.persistences.UserBodyRepository;
import com.kosta.finalProject.persistences.UserRepository;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Service
public class LoginService implements UserDetailsService {
	@Autowired
	private LoginRepository repo;
	@Autowired
	private BusinessRepository repo2;
	@Autowired
	UserBodyRepository bodyrepo;
	@Autowired
	UserRepository userrpo;
    @Autowired
    private PasswordEncoder passwordEncoder;

  	public UserVO selectById(String userId) {
  		return userrpo.findById(userId).get();
  	}
  	
	public void profileSave(UserBodyVO body) {
		  UserBodyVO newbody =UserBodyVO.builder()
			  .userAge(body.getUserAge())
			  .gender(body.getGender())
			 .height(body.getHeight()) 
			 .weight(body.getWeight())
			 .userImage(body.getUserImage())
			 .user(body.getUser()).build();
		  bodyrepo.save(newbody);
		 
	}

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { 
    	  UserDetails user = null;
    	  Optional<UserVO> obj =  repo.findById(userId) ;
    	  if(!(obj.isEmpty())) {
    		  user = repo.findById(userId)
    	        	  .filter(u ->u!=null).map(u->new SecurityUser(u)).get();
    	  }else {
    		  user = repo2.findById(userId)
                      .filter(u ->u!=null).map(u->new SecurityBusiness(u)).get();
    	  }
          return user;
      }
 
    public void signup(UserVO user, UserAddress userAddress) {
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         user.setUserPw(passwordEncoder.encode(user.getUserPw()));
         user.setUrole(UserRoleEnumType.USER);
         user.setUserAddress(userAddress);
         
        repo.save(user);		
      }
      
    public void businessSingup(BusinessVO business, BusinessAddress businessAddress) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	business.setBusinessPassword(passwordEncoder.encode(business.getBusinessPassword()));
    	  
    	repo2.save(business);
      }
      
      public void certifiedPhoneNumber(String user_phone, String cerNum) {
           String api_key = "NCS6FQ4VTRUTGQQT";
           String api_secret = "7PJ87LGTUYGTHHIIXUW1V119FZN590EF";
           Message coolsms = new Message(api_key, api_secret);
           HashMap<String, String> params = new HashMap<String, String>();
           params.put("to", user_phone);    // 수신전화번호
           params.put("from", "01033680126");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
           params.put("type", "SMS");
           params.put("text", "[ FitPle ] 휴대폰인증 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
           params.put("app_version", "test app 1.2"); // application name and version

           try {
               JSONObject obj = (JSONObject) coolsms.send(params);
           } catch (CoolsmsException e) {
               System.out.println(e.getMessage());
               System.out.println(e.getCode());
           }

       } 
      
      public void certifiedPhoneNumber2(String businessPhone, String cerNum) {
           String api_key = "NCS6FQ4VTRUTGQQT";
           String api_secret = "7PJ87LGTUYGTHHIIXUW1V119FZN590EF";
           Message coolsms = new Message(api_key, api_secret);
           HashMap<String, String> params = new HashMap<String, String>();
           params.put("to", businessPhone);    // 수신전화번호
           params.put("from", "01033680126");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
           params.put("type", "SMS");
           params.put("text", "[ FitPle ] 휴대폰인증 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
           params.put("app_version", "test app 1.2"); // application name and version

           try {
               JSONObject obj = (JSONObject) coolsms.send(params);
           } catch (CoolsmsException e) {
               System.out.println(e.getMessage());
               System.out.println(e.getCode());
           }
       }
      
      
      public boolean checkName(String userId) {
    	  return repo.findById(userId).isPresent();
      }
      
      public boolean checkNickName(String nickName) {
    	  return repo.findById(nickName).isPresent();
      }
      public boolean businessId(String businessId) {
    	  return repo2.findById(businessId).isPresent();
      }
     
}