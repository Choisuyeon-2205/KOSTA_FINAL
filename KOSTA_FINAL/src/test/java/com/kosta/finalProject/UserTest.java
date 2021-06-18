package com.kosta.finalProject;

import java.sql.Date; 
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.finalProject.models.MealCerfVO;
import com.kosta.finalProject.models.MealId;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.UserRepository;
import com.kosta.finalProject.services.LoginService;


@Commit
@SpringBootTest
public class UserTest {
	@Autowired
	UserRepository repo;
	
	@Autowired
	LoginService loginservice;
//	@Transactional
//	@Test
	public void insertMealCerfs() {
		repo.findById("test1").ifPresent(user -> {
			List<MealCerfVO> mealCerfs = user.getMealCerfs();
			MealId mealId = new MealId();
			mealId.setMealDate(new Date(System.currentTimeMillis()));
			mealId.setUser(user);
			MealCerfVO meal = MealCerfVO.builder()
					.breakfastImage("breakfast")
					.lunchImage("lunch")
					.dinnerImage("dinner")
					.mealId(mealId)
					.build();
			mealCerfs.add(meal);
			repo.save(user);
		});
	}
	
	//@Test
	public void insertUser() {
		IntStream.range(1, 6).forEach(i -> {
			UserVO user = UserVO.builder()
					.userId("test" + i)
					.userPw("qwer")
					.userName("name" + i)
					.nickName("nickname" + i)
					.userAddress("address" + i)
					.userPhone("010-1111-2222" + i)
					.userEmail("email" + i + "@gmail.com")
					.userPhone("photo" + i + ".jpg")
					.build();
			System.out.println("UserToString" + user);
			repo.save(user);
		});
	}
	@Test
	public void insertUser2() {		
	IntStream.range(1, 4).forEach(i->{
		UserVO user = UserVO.builder()
		.userId("sample" + i)
		.userPw("qwer")
		.userName("name" + i)
		.nickName("nickname" + i)
		.userAddress("address" + i)
		.userPhone("010-1111-2222" + i)
		.userEmail("email" + i + "@gmail.com")
		.userPhone("photoo"+ i+ ".jpg")
		.build();
		loginservice.joinUser(user);
	});
	}
}
