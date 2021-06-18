package com.kosta.finalProject.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@EqualsAndHashCode(of="curriculumNum")
@Table(name="curriculum")
public class CurriculumVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int curriculumNum;
	String curriculumTitle;
	String curriculumInfo;
	String curriculumDate;
	String curriculumTime;
	int curriculumAll;
	int curriculumState;
	
	@CreationTimestamp
	Timestamp curriculumFirst;
	
	@UpdateTimestamp
	Timestamp curriculumUpdate;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "centerNum",
			referencedColumnName = "centerNum"),
		@JoinColumn(name = "exerciseTypeNum",
			referencedColumnName = "exerciseTypeNum"),
	})
	ExerciseTypeRefVO etyperef;
	
	@ManyToOne
	@JoinColumn(name="trainerNum" ,referencedColumnName = "trainerNum")
	TrainerVO trainer;
}
