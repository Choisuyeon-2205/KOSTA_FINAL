package com.kosta.finalProject.persistences;

import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.BuddyVO;

public interface BuddyRepository extends CrudRepository<BuddyVO, Integer> {

}
