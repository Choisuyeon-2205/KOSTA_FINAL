package com.kosta.finalProject.persistences;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.finalProject.models.ExerciseRecordId;
import com.kosta.finalProject.models.ExerciseRecordVO;
import com.kosta.finalProject.models.QExerciseRecordVO;
import com.kosta.finalProject.models.UserVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface ExerciseRecordRepository
		extends CrudRepository<ExerciseRecordVO, ExerciseRecordId>, QuerydslPredicateExecutor<ExerciseRecordVO> {
	@Query("select e from ExerciseRecordVO e left outer join e.exerciseId.user u where u.userId = ?1")
	public List<ExerciseRecordVO> selectByUserId(String userId);

	@Query("select e from ExerciseRecordVO e left outer join e.exerciseId.user u where u.userId = ?1 and e.exerciseId.exerciseDate = ?2")
	public ExerciseRecordVO selectByUserIdAndDate(String userId, Date exerciseDate);

	public default Predicate makePredicate(UserVO user, String startDate) {
		// QuerydlsPredicateExecutor를 사용하기 위해 BooleanBuilder 사용
		BooleanBuilder builder = new BooleanBuilder();
		QExerciseRecordVO exRecord = QExerciseRecordVO.exerciseRecordVO; // 객체 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1); // 매달 1일
		String firstDay = sdf.format(cal.getTime());
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 매달 마지막 날
		String lastDay = sdf.format(cal.getTime());
		
		builder.and(exRecord.exerciseId.user.userId.eq(user.getUserId()));
		if (startDate == null) {
			builder.and(exRecord.exerciseId.exerciseDate.between(Date.valueOf(firstDay), Date.valueOf(lastDay)));
			return builder;
		}
		else {
			Date newDate = Date.valueOf(startDate);
			cal.setTime(newDate);
			firstDay = sdf.format(cal.getTime());
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 매달 마지막 날
			lastDay = sdf.format(cal.getTime());
			builder.and(exRecord.exerciseId.exerciseDate.between(Date.valueOf(firstDay), Date.valueOf(lastDay)));
			return builder;
		}
	}
}