package com.kosta.finalProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.finalProject.persistences.UserRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class UserBodyTest {

	@Autowired
	UserRepository repo;
	
	//@Test
	public void test1() {
		repo.selectAll().forEach(user->{
			log.info(user.toString());
		});
	}
	
	
}
