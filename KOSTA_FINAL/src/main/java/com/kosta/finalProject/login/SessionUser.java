package com.kosta.finalProject.login;

import lombok.Getter;

import java.io.Serializable;

import com.kosta.finalProject.models.UserVO;

/**
 * 유저 정보를 세션에 등록하기 위한 직렬화 객체
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserVO user) {
        this.name = user.getUserName();
        this.email = user.getUserEmail();
        this.picture = user.getUserPhoto();
    }
}