package com.rateye.shop_cms.application.users;

import com.rateye.shop_cms.domain.users.UserCommand;
import com.rateye.shop_cms.domain.users.UserCriteria;
import com.rateye.shop_cms.domain.users.UserInfo;
import com.rateye.shop_cms.domain.users.UserService;
import com.rateye.shop_cms.domain.users.token.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfo registerUser(UserCommand.RegisterUserRequest command) {
        return userService.registerUser(command);
    }

    public TokenInfo loginUser(UserCriteria.LoginUserRequest criteria) {
        return userService.loginUser(criteria);
    }

    public TokenInfo reissueToken(UserCriteria.ReissueTokenRequest criteria) {
        return userService.reissueToken(criteria);
    }

    public void logoutUser(UserCriteria.LogoutRequest criteria) {
        userService.logoutUser(criteria);
    }
}
