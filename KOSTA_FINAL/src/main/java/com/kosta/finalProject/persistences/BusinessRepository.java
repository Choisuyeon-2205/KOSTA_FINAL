package com.kosta.finalProject.persistences;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.BusinessVO;

public interface BusinessRepository extends CrudRepository<BusinessVO, String>{

}
