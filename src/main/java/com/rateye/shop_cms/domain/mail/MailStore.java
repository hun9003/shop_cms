package com.rateye.shop_cms.domain.mail;

public interface MailStore {
    Mail save(Mail mail);
    void delete(Mail mail);
}
