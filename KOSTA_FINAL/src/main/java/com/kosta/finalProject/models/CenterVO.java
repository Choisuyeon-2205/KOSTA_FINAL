package com.kosta.finalProject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@EqualsAndHashCode(of="centerNum")
@Table(name="center")
public class CenterVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CENTER_SEQ_GENERATOR")
	int centerNum;
	String corporateNum;
	String centerTitle;
	String centerInfo;
	Long centerPreferance;
	String centerAddress;
	String centerArea1;
	String centerArea2;
	
	
	//여러 개의 center은 하나의 사업자(business)을 참조한다.
	@ManyToOne
	BusinessVO business; //fk  business_business 칼럼 추가된다.

	/*
	@JsonIgnore
	@OneToMany(mappedBy = "center", //fk이름 "메여있다"
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY) //fetch = FetchType.EAGER
	List<ExerciseTypeRefVO> etypes;
	*/
}
