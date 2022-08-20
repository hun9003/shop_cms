package com.rateye.shop_cms.domain.auth;

import com.rateye.shop_cms.domain.users.token.TokenInfo;

public interface AuthService {
    void register(AuthCommand.RegisterRequest command);
    TokenInfo login(AuthCriteria.LoginRequest criteria);
    TokenInfo reissueToken(AuthCriteria.ReissueTokenRequest criteria);
    void logout(AuthCriteria.LogoutRequest criteria);

}
