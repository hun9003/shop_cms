package com.rateye.shop_cms.domain.users.token;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Builder
public class TokenInfo {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
    private final Long refreshTokenExpirationTime;
    private final Collection<? extends GrantedAuthority> roles;

    public TokenInfo(String grantType, String accessToken, String refreshToken, Long refreshTokenExpirationTime, Collection<? extends GrantedAuthority> roles) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.roles = roles;
    }
}
