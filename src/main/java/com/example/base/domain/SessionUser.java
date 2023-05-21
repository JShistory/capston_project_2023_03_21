package com.example.base.security;


import com.example.base.domain.Role;
import com.example.base.domain.User;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private Role role;

    /**
     * 이 생성자는 User 객체에서 이름과 이메일 정보를 가져와서 SessionUser 객체에 저장합니다.
     */
    public SessionUser(User user) {
        this.name = User.getName();
        this.email = User.getEmail();
        this.role = User.getRole();
    }
}
