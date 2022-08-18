package com.rateye.shop_cms.domain.users;

import lombok.Getter;

import java.util.List;

@Getter
public class UserInfo {
    private final String id;
    private final List<String> roles;

    public UserInfo(User user) {
        this.id = user.getId();
        this.roles = user.getRoles();
    }

}
