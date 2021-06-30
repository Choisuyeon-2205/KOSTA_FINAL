package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.UserBodyVO;
import com.kosta.finalProject.persistences.UserBodyRepository;

@Service
public class UserBodyService {
	@Autowired
	UserBodyRepository ubRepository;

	public UserBodyVO selectById(int body_num) {
		return ubRepository.findById(body_num).get();
	}
	
	public UserBodyVO selectByUser(String userid) {
		return ubRepository.findByUser(userid);
	}
	
	public List<UserBodyVO> findIsBuddy(String userId, int bmiGroup) {
		return (List<UserBodyVO>)ubRepository.findIsBuddy(userId, bmiGroup);
	}
	
	public List<UserBodyVO> selectUserBody() {
		return (List<UserBodyVO>)ubRepository.findAll();
	}
	
	public UserBodyVO updateUserBody(UserBodyVO userbody) {
		return ubRepository.save(userbody);
	}
	
	public int deleteUserBody(int body_num) {
		//삭제하기
		int ret=0;
		try {
			ubRepository.deleteById(body_num);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
