package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.BuddyVO;
import com.kosta.finalProject.persistences.BuddyRepository;

@Service
public class BuddyService {
	@Autowired
	BuddyRepository buddyRepo;
	
	public BuddyVO selectById(int buddy_num) {
		return buddyRepo.findById(buddy_num).get();
	}
	
	
	public List<BuddyVO> selectAll() {
		return (List<BuddyVO>)buddyRepo.findAll();
	}
	
	public BuddyVO selectByUserId(String user_id) {
		return buddyRepo.findUserid(user_id);
	}
	
	
	public BuddyVO insertOrUpdateBuddy(BuddyVO buddy) {
		return buddyRepo.save(buddy);
	}
	
	public int deleteBuddy(int buddy_num) {
		//삭제하기
		int ret=0;
		try {
			buddyRepo.deleteById(buddy_num);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}
}
