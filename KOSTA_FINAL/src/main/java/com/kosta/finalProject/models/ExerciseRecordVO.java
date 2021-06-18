package com.kosta.finalProject.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "exercise_record_tb")
public class ExerciseRecordVO {
	@EmbeddedId
	private ExerciseRecordId exerciseId;
	
	@Column(name = "exercise_type")
	private String exerciseType;
	@Column(name = "exercise_time")
	private int exerciseTime;
	@Column(name = "exercise_image")
	private String exerciseImage;
}
