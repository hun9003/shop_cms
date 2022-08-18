package com.rateye.shop_cms.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserCriteria {

    @Getter
    @Builder
    @ToString
    public static class ReissueTokenRequest {
        private final String accessToken;
        private final String refreshToken;
    }

    @Getter
    @Builder
    @ToString
    public static class LoginUserRequest {
        private final String id;
        private final String password;

        public LoginUserRequest(String id, String password) {
            this.id = id;
            this.password = password;
        }

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(id, password);
        }
    }

    @Getter
    @Builder
    @ToString
    public static class LogoutRequest {
        private final String accessToken;
        private final String refreshToken;
    }
}
