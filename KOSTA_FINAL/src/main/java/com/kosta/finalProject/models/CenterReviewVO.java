package com.kosta.finalProject.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="crNum")
@Table(name="centerreview")
public class CenterReviewVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int crNum;
	String crContents;
	Long crPreference;
	
	@CreationTimestamp
	Timestamp crFirst;
	@UpdateTimestamp
	Timestamp crUpdate;
	
	@ManyToOne
	@JoinColumn(name = "userId" , referencedColumnName = "user_id")
	UserVO user;
	
	@ManyToOne
	@JoinColumn(name = "curriculumNum" , referencedColumnName ="curriculumNum")
	CurriculumVO curriculum;
	
	@ManyToOne
	@JoinColumn(name = "centerNum" , referencedColumnName ="centerNum")
	CenterVO center;
}
