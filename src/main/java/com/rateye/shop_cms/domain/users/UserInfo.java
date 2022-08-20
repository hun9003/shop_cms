package com.rateye.shop_cms.domain.users;

import lombok.Getter;

import java.util.List;

@Getter
public class UserInfo {
    private final String email;
    private final String name;
    private final List<String> roles;

    public UserInfo(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.roles = user.getRoles();
    }

}
