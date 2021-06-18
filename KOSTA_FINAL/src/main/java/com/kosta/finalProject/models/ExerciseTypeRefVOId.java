package com.kosta.finalProject.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class ExerciseTypeRefVOId implements Serializable{
	
	@ManyToOne
	@JoinColumn(name = "centerNum" , referencedColumnName = "centerNum")
	CenterVO center;
	
	@ManyToOne
	@JoinColumn(name = "exerciseTypeNum" , referencedColumnName ="exerciseTypeNum")
	ExerciseTypeVO etype;
}
