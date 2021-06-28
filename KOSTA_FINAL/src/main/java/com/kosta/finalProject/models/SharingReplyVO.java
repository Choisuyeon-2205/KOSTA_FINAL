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
@ToString(exclude = "sboard")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "shareRplNum")
@Table(name = "SHARING_BOARD_REPLY_TB")
public class SharingReplyVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int shareRplNum;
	
	@ManyToOne
	UserVO user; // FK 참조키 
	
	@ManyToOne
	SharingBoardVO sboard; //FK 참조키
	

	@Column(name= "CONTENT")
	String content;
	
	@Column(name = "CREATION_DATE")
	@CreationTimestamp
	Timestamp creationDate;
}
