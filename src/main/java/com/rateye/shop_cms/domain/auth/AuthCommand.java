package com.rateye.shop_cms.domain.auth;

import com.rateye.shop_cms.common.util.PasswordGenerator;
import com.rateye.shop_cms.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;

public class AuthCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterRequest {
        private final String email;
        private final String name;
        private final String password;
        private final String role;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .name(name)
                    .password(PasswordGenerator.passwordEncoder(password))
                    .roles(Collections.singletonList(role))
                    .build();
        }
    }
}
