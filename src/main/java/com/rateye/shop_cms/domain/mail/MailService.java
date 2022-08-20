package com.rateye.shop_cms.domain.mail;

public interface MailService {
    void forgetPassword(MailCommand.ForgetPasswordRequest command);
}
