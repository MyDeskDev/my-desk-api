package com.mydesk.api.config.auth.dto;

import com.mydesk.api.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String profileImgUrl;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImgUrl = user.getProfileImgUrl();
    }
}
