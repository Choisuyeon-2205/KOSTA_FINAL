package com.kosta.finalProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.finalProject.models.AreasVO;
import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.services.AreasService;
import com.kosta.finalProject.services.CenterService;

@RestController
public class AreaController {
	@Autowired
	AreasService areaService;
	@Autowired
	CenterService centerService;
	
	@GetMapping("/center/getAll/{value}")
	public ResponseEntity<List<CenterVO>> getAll(@PathVariable int value) {
		
		return new ResponseEntity<>(centerService.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping("/center/getArea2/{area1}")
	public ResponseEntity<List<AreasVO>> getArea2(@PathVariable String area1) {
		
		return new ResponseEntity<>(areaService.selectByArea1(area1), HttpStatus.OK);
	}
	
	@GetMapping("/center/getByArea1/{area1}")
	public ResponseEntity<List<CenterVO>> getByArea1(@PathVariable String area1) {
		
		return new ResponseEntity<>(centerService.selectByArea1(area1), HttpStatus.OK);
	}
	
	
	@GetMapping("/center/getByArea2/{area2}")
	public ResponseEntity<List<CenterVO>> getByArea2(@PathVariable String area2) {
		
		return new ResponseEntity<>(centerService.selectByArea2(area2), HttpStatus.OK);
	}
}
