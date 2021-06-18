package com.kosta.finalProject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
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
@EqualsAndHashCode(of="businessId")
@Table(name="business")
public class BusinessVO {
	@Id //필수PK
	String businessId;
	String businessPassword;
	String businessTitle;
	String businessAddress;
	String businessPhone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "business", //fk이름 "메여있다"
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY) //fetch = FetchType.EAGER
	List<CenterVO> centers;
	
}
