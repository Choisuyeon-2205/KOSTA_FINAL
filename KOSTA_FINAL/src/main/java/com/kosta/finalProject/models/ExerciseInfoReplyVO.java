package com.kosta.finalProject.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "eboard")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "infoRplNum")
@Table(name = "EXERCISE_INFO_REPLY_TB")
public class ExerciseInfoReplyVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int infoRplNum;
	
	@ManyToOne
	UserVO user; // FK 참조키 
	
	@ManyToOne
	ExerciseInfoBoardVO eboard; //FK 참조키
	

	@Column(name= "CONTENT")
	String content;
	
	@Column(name = "CREATION_DATE")
	@CreationTimestamp
	Timestamp creationDate;
}
