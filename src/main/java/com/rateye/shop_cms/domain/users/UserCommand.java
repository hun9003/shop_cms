package com.rateye.shop_cms.domain.users;

import com.rateye.shop_cms.domain.users.role.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;

public class UserCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterUserRequest {
        private final String id;
        private final String password;

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .password(password)
                    .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                    .build();
        }
    }
}
