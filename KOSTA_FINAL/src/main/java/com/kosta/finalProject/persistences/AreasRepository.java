package com.kosta.finalProject.persistences;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.AreasVO;

public interface AreasRepository  extends CrudRepository<AreasVO, Integer>{
	@Query(value="select distinct area1 from areas", nativeQuery = true)
	public Object[] findAllArea1();
	
	public List<AreasVO> findByArea1(String area1);
}
