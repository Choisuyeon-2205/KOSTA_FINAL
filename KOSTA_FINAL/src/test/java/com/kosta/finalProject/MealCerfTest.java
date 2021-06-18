package com.kosta.finalProject;

import java.sql.Date;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.finalProject.models.MealCerfVO;
import com.kosta.finalProject.models.MealId;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.MealCerfRepository;

@SpringBootTest
public class MealCerfTest {
	
	@Autowired
	MealCerfRepository repo;
	
	@Test
	public void insertMealCerfs() {
		IntStream.range(1, 6).forEach(i -> {
			MealId mealId = new MealId();
			mealId.setMealDate(new Date(System.currentTimeMillis()));
			UserVO user = new UserVO();
			user.setUserId("test" + i);
			mealId.setUser(user);
			MealCerfVO meal = MealCerfVO.builder()
					.mealId(mealId).build();
			repo.save(meal);
		});
	}

}
