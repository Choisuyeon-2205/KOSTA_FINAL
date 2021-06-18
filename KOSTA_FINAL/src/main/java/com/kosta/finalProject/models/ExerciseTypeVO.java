package com.kosta.finalProject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@EqualsAndHashCode(of="exerciseTypeNum")
@Table(name="exercisetype")
public class ExerciseTypeVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int exerciseTypeNum;
	String exerciseTypeName;
	/*
	//@JsonIgnore
	@OneToMany(mappedBy = "etype", //fk이름 "메여있다"
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY) //fetch = FetchType.EAGER
	List<ExerciseTypeRefVO> etypes;
	*/
}
