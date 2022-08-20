package com.rateye.shop_cms.domain.mail;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MailInfo {
    private final String email;
    private final String code;

    public MailInfo(Mail mail) {
        this.email = mail.getEmail();
        this.code = mail.getCode();
    }
}
