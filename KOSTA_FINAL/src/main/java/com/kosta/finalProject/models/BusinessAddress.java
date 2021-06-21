package com.kosta.finalProject.models;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@ToString
public class BusinessAddress {


			private String AddNum;
			private String Address1;
			private String Address2;
			private String Address3;
	}