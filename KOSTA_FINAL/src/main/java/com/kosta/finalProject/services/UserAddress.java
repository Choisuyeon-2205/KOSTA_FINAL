package com.kosta.finalProject.services; 

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
public class UserAddress {
		private String AddNum;
		private String userAddress1;
		private String userAddress2;
		private String userAddress3;
}
