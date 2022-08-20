package com.rateye.shop_cms.domain.mail;

import com.rateye.shop_cms.domain.auth.AuthCommand;

public interface MailService {
    void forgetPassword(AuthCommand.ForgetPasswordRequest command);
}
