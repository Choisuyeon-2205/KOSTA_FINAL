package com.kosta.finalProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(of="trainerNum")
@Table(name="trainer")
public class TrainerVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int trainerNum;
	String trainerName;
	String trainerPhone;
	String trainerEmail;
	String trainerInfo;
	String trainerPhoto;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "centerNum",
			referencedColumnName = "centerNum"),
		@JoinColumn(name = "exerciseTypeNum",
			referencedColumnName = "exerciseTypeNum"),
	})
	ExerciseTypeRefVO etyperef;
	
}