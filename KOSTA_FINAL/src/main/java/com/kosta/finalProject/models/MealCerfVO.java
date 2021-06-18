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
@Table(name = "meal_cerf_tb")
public class MealCerfVO {
	@EmbeddedId
	private MealId mealId;
	
	@Column(name = "breakfast_image")
	private String breakfastImage;
	@Column(name = "lunch_image")
	private String lunchImage;	
	@Column(name = "dinner_image")
	private String dinnerImage;
}
