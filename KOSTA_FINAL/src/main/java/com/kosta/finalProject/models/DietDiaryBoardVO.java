package com.kosta.finalProject.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "dreplies")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DIET_DIARY_TB") 
public class DietDiaryBoardVO {
	@Id
	@Column(name= "DIARY_NUM")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int diaryNum;
	
	@ManyToOne
	UserVO user; //user_id 가져오는곳
	
	
	@Column(name= "TITLE")
	String title;
	
	
	@Column(name= "CONTENT")
	String content;
	
	@Column(name= "PHOTO")
	String photo;
	
	@Column(name = "CREATION_DATE")
	@CreationTimestamp
	Timestamp creationDate;
	
	@Column(name = "MODIFIED_DATE")
	@UpdateTimestamp
	Timestamp modifiedDate;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "dboard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<DietDiaryReplyVO> dreplies; 
	
	
}
