package com.kosta.finalProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
