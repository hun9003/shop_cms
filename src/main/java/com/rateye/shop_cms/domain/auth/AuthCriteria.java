package com.rateye.shop_cms.domain.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthCriteria {

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
    public static class LoginRequest {
        private final String email;
        private final String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
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
