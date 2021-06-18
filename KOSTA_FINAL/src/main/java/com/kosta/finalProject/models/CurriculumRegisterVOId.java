package com.kosta.finalProject.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class CurriculumRegisterVOId implements Serializable{
	@ManyToOne
	@JoinColumn(name = "userId" , referencedColumnName = "user_id")
	UserVO user;
	
	@ManyToOne
	@JoinColumn(name = "curriculumNum" , referencedColumnName ="curriculumNum")
	CurriculumVO curriculum;
}
