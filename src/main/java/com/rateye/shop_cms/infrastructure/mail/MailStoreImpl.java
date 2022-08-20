package com.rateye.shop_cms.infrastructure.mail;

import com.rateye.shop_cms.domain.mail.Mail;
import com.rateye.shop_cms.domain.mail.MailStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailStoreImpl implements MailStore {
    private final MailRepository mailRepository;

    @Override
    public Mail save(Mail mail) {
        return mailRepository.save(mail);
    }
}
