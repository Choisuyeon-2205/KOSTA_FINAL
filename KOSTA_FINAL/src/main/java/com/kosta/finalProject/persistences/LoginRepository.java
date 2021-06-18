package com.kosta.finalProject.persistences;



import org.springframework.data.repository.CrudRepository;
import com.kosta.finalProject.models.UserVO;

public interface LoginRepository extends CrudRepository<UserVO, String> {
}

