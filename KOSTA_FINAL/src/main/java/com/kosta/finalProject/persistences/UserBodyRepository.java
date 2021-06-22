package com.kosta.finalProject.persistences;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.UserBodyVO;

public interface UserBodyRepository extends CrudRepository<UserBodyVO, String> {

}
