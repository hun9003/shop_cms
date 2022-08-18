package com.rateye.shop_cms.interfaces.users;

import com.rateye.shop_cms.domain.users.UserInfo;
import com.rateye.shop_cms.domain.users.token.TokenInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class UserResponseDto {

    @Getter
    @ToString
    public static class RegisterMemberResponse {
        @ApiModelProperty(name = "id", example = "test12345", notes = "가입 처리된 아이디 입니다.")
        private final String id;

        @ApiModelProperty(name = "roles", notes = "유저의 권한 입니다.")
        private final List<String> roles;

        public RegisterMemberResponse(UserInfo userInfo) {
            this.id = userInfo.getId();
            this.roles = userInfo.getRoles();
        }
    }

    @Getter
    @ToString
    public static class TokenInfoResponse {
        @ApiModelProperty(name = "grantType",
                example = "Bearer",
                notes = "유저의 토큰 유형 입니다.")
        private final String grantType;

        @ApiModelProperty(name = "accessToken",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzNDUiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjU5NDQ2NjU0fQ.WLmpRAaJ60LtLOrBmKkWNYOy1BOZw-hG8CuZEVlMKXw",
                notes = "유저의 엑세스 토큰 입니다.")
        private final String accessToken;

        @ApiModelProperty(name = "accessToken",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjAwNDk2NTR9.ExqgYevzLDn6Z2YPBpaA0mCFODlSjdRZgAokUe_zr98",
                notes = "유저의 리프레시 토큰 입니다.")
        private final String refreshToken;

        @ApiModelProperty(name = "refreshTokenExpirationTime",
                example = "604800000",
                notes = "유저의 리프레시 토큰 유효시간 입니다.")
        private final Long refreshTokenExpirationTime;

        public TokenInfoResponse(TokenInfo tokenInfo) {
            this.grantType = tokenInfo.getGrantType();
            this.accessToken = tokenInfo.getAccessToken();
            this.refreshToken = tokenInfo.getRefreshToken();
            this.refreshTokenExpirationTime = tokenInfo.getRefreshTokenExpirationTime();
        }
    }
}
