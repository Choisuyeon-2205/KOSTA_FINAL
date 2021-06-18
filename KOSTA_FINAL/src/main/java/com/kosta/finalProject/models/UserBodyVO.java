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

import lombok.Getter;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_body_TB")
public class UserBodyVO {
	
	@Id
	@Column(name="body_num")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BODY_SEQ_GENERATOR")
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
	@Column(name="user_BMI" , nullable = true)
	int userBmi;
	
	@OneToOne // 부모는 자식을 모르지만, 자식은 부모를 알아야 한다!
	@JoinColumn(name = "user_id")
	private UserVO user;
}
