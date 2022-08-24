package com.rateye.shop_cms.domain.auth;

import com.rateye.shop_cms.domain.users.UserInfo;
import com.rateye.shop_cms.domain.users.token.TokenInfo;

public interface AuthService {
    void register(AuthCommand.RegisterRequest command);
    TokenInfo login(AuthCriteria.LoginRequest criteria);
    TokenInfo reissueToken(AuthCriteria.ReissueTokenRequest criteria);
    void logout(AuthCriteria.LogoutRequest criteria);
    void resetPassword(AuthCommand.ResetPasswordRequest command);
    void changePassword(AuthCommand.ChangePassword command, String token);
    UserInfo.Me me(String token);
}
