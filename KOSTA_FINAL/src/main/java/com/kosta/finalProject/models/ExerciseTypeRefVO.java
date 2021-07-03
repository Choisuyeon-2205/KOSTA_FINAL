package com.kosta.finalProject.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
