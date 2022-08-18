package com.rateye.shop_cms.domain.users;

import com.rateye.shop_cms.domain.users.token.TokenInfo;

public interface UserService {
    UserInfo registerUser(UserCommand.RegisterUserRequest command);
    TokenInfo loginUser(UserCriteria.LoginUserRequest criteria);
    TokenInfo reissueToken(UserCriteria.ReissueTokenRequest criteria);
    void logoutUser(UserCriteria.LogoutRequest criteria);
}
