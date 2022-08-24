package com.rateye.shop_cms.application.auth;

import com.rateye.shop_cms.domain.auth.AuthCommand;
import com.rateye.shop_cms.domain.auth.AuthCriteria;
import com.rateye.shop_cms.domain.auth.AuthService;
import com.rateye.shop_cms.domain.mail.MailCommand;
import com.rateye.shop_cms.domain.mail.MailCriteria;
import com.rateye.shop_cms.domain.mail.MailService;
import com.rateye.shop_cms.domain.users.UserInfo;
import com.rateye.shop_cms.domain.users.token.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    private final MailService mailService;

    public TokenInfo register(AuthCommand.RegisterRequest command) {
        authService.register(command);
        return authService.login(AuthCriteria.LoginRequest.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .build());
    }

    public TokenInfo login(AuthCriteria.LoginRequest criteria) {
        return authService.login(criteria);
    }

    public void forgetPassword(MailCommand.ForgetPasswordRequest command) {
        mailService.forgetPassword(command);
    }

    public void verifyForgetPassword(MailCriteria.VerifyForgetPasswordRequest criteria) {
        mailService.verifyForgetPassword(criteria);
    }

    public void resetPassword(AuthCommand.ResetPasswordRequest command) {
        mailService.verifyForgetPassword(MailCriteria.VerifyForgetPasswordRequest.builder()
                .email(command.getEmail())
                .token(command.getToken()).build());
        authService.resetPassword(command);
    }

    public TokenInfo reissueToken(AuthCriteria.ReissueTokenRequest criteria) {
        return authService.reissueToken(criteria);
    }

    public void logout(AuthCriteria.LogoutRequest criteria) {
        authService.logout(criteria);
    }

    public void changePassword(AuthCommand.ChangePassword command, String token) {
        authService.changePassword(command, token);
    }

    public UserInfo.Me me(String token) {
        return authService.me(token);
    }
}
