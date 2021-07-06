package com.kosta.finalProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(of="centerNum")
@Table(name="center")
public class CenterVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int centerNum;
	String corporateNum;
	String centerTitle;
	String centerInfo;
	String centerPhone;
	Long centerPreferance;
	String centerAddress;
	String centerArea1;
	String centerArea2;
	
	
	//여러 개의 center은 하나의 사업자(business)을 참조한다.
	@ManyToOne
	BusinessVO business; //fk  business_business 칼럼 추가된다.
}
