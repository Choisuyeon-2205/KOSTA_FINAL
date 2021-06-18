package com.kosta.finalProject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name="exercisetyperef")
public class ExerciseTypeRefVO {
	@EmbeddedId
	ExerciseTypeRefVOId id;
	
	/*
	@JsonIgnore
	@OneToMany(mappedBy = "etyperef", //fk이름 "메여있다"
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY) //fetch = FetchType.EAGER
	List<TrainerVO> trainers;
	*/
	
}
