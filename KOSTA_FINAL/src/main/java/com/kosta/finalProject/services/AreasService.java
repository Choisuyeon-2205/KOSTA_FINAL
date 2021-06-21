package com.kosta.finalProject.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.AreasVO;
import com.kosta.finalProject.persistences.AreasRepository;

@Service
public class AreasService {
	@Autowired
	AreasRepository areaRepo;
	
	public AreasVO selectById(int area_id) {
		return areaRepo.findById(area_id).get();
	}
	
	public List<AreasVO> selectAll() {
		return (List<AreasVO>)areaRepo.findAll();
	}
	
	public String[] selectAllArea1() {
		
		return Arrays.copyOf(areaRepo.findAllArea1(), areaRepo.findAllArea1().length, String[].class);
	}
	
	public List<AreasVO> selectByArea1(String area1) {
		return areaRepo.findByArea1(area1);
	}
}
