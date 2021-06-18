package com.kosta.finalProject;

import java.sql.Date;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.finalProject.models.ExerciseRecordId;
import com.kosta.finalProject.models.ExerciseRecordVO;
import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.ExerciseRecordRepository;

@SpringBootTest
public class ExerciseRecordTest {

	@Autowired
	ExerciseRecordRepository repo;
	
	@Test
	public void insertExerciseRecords() {
		IntStream.range(1, 6).forEach(i -> {
			ExerciseRecordId exerciseId = new ExerciseRecordId();
			exerciseId.setExerciseDate(new Date(System.currentTimeMillis()));
			UserVO user = new UserVO();
			user.setUserId("test" + i);
			exerciseId.setUser(user);
			ExerciseRecordVO exercise = ExerciseRecordVO.builder()
					.exerciseId(exerciseId)
					.exerciseType("조깅")
					.exerciseTime(55 + i)
					.exerciseImage("인증이미지" + i + ".jpg")
					.build();
			repo.save(exercise);
		});
	}
}
