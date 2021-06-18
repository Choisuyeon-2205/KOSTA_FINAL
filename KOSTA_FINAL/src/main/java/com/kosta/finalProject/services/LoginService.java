package com.kosta.finalProject.services;


 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kosta.finalProject.login.SecurityUser;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.LoginRepository;


@Service
public class LoginService implements UserDetailsService {
	  @Autowired
      private LoginRepository repo;
        
      @Autowired
      private PasswordEncoder passwordEncoder;
        

      public UserVO joinUser(UserVO user) {
    	  System.out.println("join user에서 찍음"+ user);
    	  user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		return repo.save(user);
      }
      
      @Override
      public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { 
    	  System.out.println(userId + "요기");
          UserDetails user = repo.findById(userId)
        		  .filter(u ->u!=null).map(u->new SecurityUser(u)).get();
          	return user;
      }
}