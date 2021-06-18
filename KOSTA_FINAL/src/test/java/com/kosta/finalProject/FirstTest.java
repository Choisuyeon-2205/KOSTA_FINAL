package com.kosta.finalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.finalProject.models.BusinessVO;
import com.kosta.finalProject.models.CenterVO;
import com.kosta.finalProject.models.CurriculumVO;
import com.kosta.finalProject.models.ExerciseTypeRefVO;
import com.kosta.finalProject.models.ExerciseTypeRefVOId;
import com.kosta.finalProject.models.ExerciseTypeVO;
import com.kosta.finalProject.models.TrainerVO;
import com.kosta.finalProject.persistences.BusinessRepository;
import com.kosta.finalProject.persistences.CenterRepository;
import com.kosta.finalProject.persistences.CurriculumRepository;
import com.kosta.finalProject.persistences.ExerciseTypeRefRepository;
import com.kosta.finalProject.persistences.ExerciseTypeRepository;
import com.kosta.finalProject.persistences.TrainerRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class FirstTest {
	@Autowired 
	BusinessRepository brepository;
	@Autowired
	CenterRepository crepository;
	@Autowired
	ExerciseTypeRepository etrepository;
	@Autowired
	ExerciseTypeRefRepository etrrepository;
	@Autowired
	TrainerRepository trepository;
	@Autowired
	CurriculumRepository currepository;
	
	//@Test //여러건 insert
	public void insertBusiness() {
		IntStream.range(1,3).forEach(i->{
			BusinessVO b= new BusinessVO();
			b.setBusinessId("business"+i);
			b.setBusinessPassword("bp"+i);
			b.setBusinessAddress("서울시 금천구"+i);
			b.setBusinessTitle("사업자"+i);
			b.setBusinessPhone("010-1234-56"+i);
			
			List<CenterVO> centers= new ArrayList<>();
			IntStream.range(1, 4).forEach(j->{
				CenterVO center= new CenterVO();
				center.setBusiness(b);
				center.setCenterInfo(b.getBusinessTitle()+"의 센터"+i);
				center.setCenterPreferance(4L);
				center.setCenterTitle("센터"+center.getCenterNum());
				center.setCorporateNum("12313264"+i);
				center.setCenterAddress("서울시 금천구 가산동"+i);
				center.setCenterArea1("지역"+i);
				center.setCenterArea2("상세 지역"+i);
				centers.add(center);
			});
			b.setCenters(centers);
			brepository.save(b);
		});
	}
	
	//@Test //운동유형 insert
	public void insertExerciseType() {
		ExerciseTypeVO et= new ExerciseTypeVO();
		et.setExerciseTypeName("그룹PT"); //헬스 수영 필라테스 요가 플라잉요가 방송댄스 줌바댄스 스피닝 복싱 개인PT 그룹PT
		etrepository.save(et);
	}
	
	//@Test //typeref insert
	public void insertExerciseTypeRef() {
		ExerciseTypeRefVO etr= new ExerciseTypeRefVO();
		CenterVO c= new CenterVO();
		c.setCenterNum(5);
		ExerciseTypeVO e= new ExerciseTypeVO();
		e.setExerciseTypeNum(11);
		
		ExerciseTypeRefVOId refvoid= new ExerciseTypeRefVOId();
		refvoid.setCenter(c);
		refvoid.setEtype(e);
		etr.setId(refvoid);
		etrrepository.save(etr);
	}
	
	//@Test
	public void insert3datas() {
		
		CenterVO c= new CenterVO();
		BusinessVO b= new BusinessVO();
		b.setBusinessId("business1");
		c.setBusiness(b);
		c.setCenterInfo("kosta의 첫번째 체육관입니다.");
		c.setCenterTitle("kosta센터1");
		c.setCenterPreferance(5L);
		c.setCorporateNum("216131321");
		
		List<ExerciseTypeRefVO> etrlist= new ArrayList<>();
		ExerciseTypeRefVO etr= new ExerciseTypeRefVO();
		//etr.setCenter(c);
		ExerciseTypeVO et= new ExerciseTypeVO();
		et.setExerciseTypeName("방송댄스");
		//etr.setEtype(et);
		etrlist.add(etr);
		//c.setEtypes(etrlist);
		
		crepository.save(c);
	}
	
	//@Test
	public void insertTrainer() {
		IntStream.range(7,10).forEach(i->{
			TrainerVO t= new TrainerVO();
			t.setTrainerName("트레이너"+i);
			t.setTrainerInfo("트레이너"+i+"의 소개");
			t.setTrainerEmail("trainer"+i+"@naver.com");
			t.setTrainerPhone("010-8845-151"+i);
			t.setTrainerPhoto("trainer"+i+".jpg");
			
			ExerciseTypeRefVO etr= new ExerciseTypeRefVO();
			CenterVO c= new CenterVO();
			c.setCenterNum(3);
			ExerciseTypeVO e= new ExerciseTypeVO();
			e.setExerciseTypeNum(6);
			
			ExerciseTypeRefVOId refvoid= new ExerciseTypeRefVOId();
			refvoid.setCenter(c);
			refvoid.setEtype(e);
			etr.setId(refvoid);
			
			t.setEtyperef(etr);
			trepository.save(t);
		});
	
	}
	
	@Test
	public void insertCurriculum() {
		IntStream.range(1,3).forEach(i->{
			ExerciseTypeRefVO etr= new ExerciseTypeRefVO();
			CenterVO c= new CenterVO();
			c.setCenterNum(3);
			ExerciseTypeVO e= new ExerciseTypeVO();
			e.setExerciseTypeNum(6);
			
			ExerciseTypeRefVOId refvoid= new ExerciseTypeRefVOId();
			refvoid.setCenter(c);
			refvoid.setEtype(e);
			etr.setId(refvoid);
			
			TrainerVO t= new TrainerVO();
			t.setTrainerNum(7);
			
			CurriculumVO cur= new CurriculumVO();
			cur.setTrainer(t);
			cur.setEtyperef(etr);
			cur.setCurriculumAll(15);
			cur.setCurriculumDate("월,수,금");
			cur.setCurriculumTime("18시~19시");
			cur.setCurriculumInfo("트레이너7의 강좌입니다.");
			cur.setCurriculumState(0);
			cur.setCurriculumTitle("트레이너7과 함께 방송댄스를");
			currepository.save(cur);
			
		});
	}
}
