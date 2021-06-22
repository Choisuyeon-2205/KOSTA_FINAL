package com.kosta.finalProject;

import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.UserBodyRepository;
import com.kosta.finalProject.persistences.UserRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class UserBodyTest {

	@Autowired
	UserBodyRepository repo;
	
	@Test
	public void test1() {
		UserVO user = UserVO.builder().userId("oh2").build();
		 UserBodyVO body = UserBodyVO.builder()
				// 
				 .gender("M")
				 .height(10.1)
				 .weight(56.4)
				 .userAge(15)
				 .user(user).build();
		
		 repo.save(body);
	}
	
	
}
