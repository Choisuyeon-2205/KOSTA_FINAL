package com.kosta.finalProject.models;

import lombok.ToString;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_body_TB")
public class UserBodyVO {
	
	@Id
	@Column(name="body_num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int bodyNum;
	@Column(length = 1) // length는 String만 사용 가능 
	String gender;
	@Column(name="user_age", length = 3)
	int userAge;

	@Column(nullable = true)
	double height;
	@Column(nullable = true)
	double weight;
	@Column(name="user_image", nullable = true)
	String userImage;
	@Column(nullable = true, name = "user_bmi")
	double userBmi ;
	@Column(nullable = true)
	int bmiGroup;
	
	@Column(nullable = true)
	int buddyCheck;


	


	// bmi = weight/((height/100) * (height/100));
	@OneToOne // 부모는 자식을 모르지만, 자식은 부모를 알아야 한다!
	@JoinColumn(name = "user_id")
	private UserVO user;
}
