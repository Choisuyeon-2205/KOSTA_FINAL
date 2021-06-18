package com.kosta.finalProject.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
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
@Builder
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "userId")
@Table(name = "user_TB")
public class UserVO {
	@Id
	@Column(name="user_id")
	String userId;
	
	@Column(name="user_password")
	String userPw;

	@Column(name="user_name", nullable = true)
	String userName;
	
	String nickName;

	@Column(name="user_address", nullable = true)
	String userAddress;
	@Column(name="user_phone", unique = true, nullable = false)
	String userPhone;
	@Column(name="user_email", nullable = true)
	String userEmail;
	@Column(name="user_photo", nullable = true)
	String userPhoto;

	UserRoleEnumType urole;


	@JsonIgnore
	@OneToMany(mappedBy = "mealId.user", cascade = CascadeType.ALL)
	List<MealCerfVO> mealCerfs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "exerciseId.user", cascade = CascadeType.ALL)
	List<ExerciseRecordVO> exerciseRecords;
}
