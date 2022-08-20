package com.rateye.shop_cms.interfaces.auth;

import com.rateye.shop_cms.common.util.PermissionGenerator;
import com.rateye.shop_cms.domain.users.token.TokenInfo;
import lombok.Getter;
import lombok.ToString;

public class AuthResponseDto {
    @Getter
    @ToString
    public static class TokenInfoResponse {
        private final String token;
        private final String[] permissions;

        public TokenInfoResponse(TokenInfo tokenInfo) {
            this.token = tokenInfo.getAccessToken();
            this.permissions = PermissionGenerator.getPermissions(tokenInfo.getRoles());
        }
    }
}
