package com.kosta.finalProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.BusinessVO;
import com.kosta.finalProject.persistences.BusinessRepository;

@Service
public class BusinessService {
	@Autowired
	BusinessRepository brepository;
	
	public BusinessVO selectById(String business_id) {
		return brepository.findById(business_id).get();
	}
	
	public List<BusinessVO> selectAll() {
		return (List<BusinessVO>)brepository.findAll();
	}
	
	public BusinessVO updateBusiness(BusinessVO business) {
		return brepository.save(business);
	}
	
	public int deleteBusiness(String business_id) {
		//삭제하기
		int ret=0;
		try {
			brepository.deleteById(business_id);
			ret=1;
		}catch (Exception e) {
		}
		return ret;
	}

}
