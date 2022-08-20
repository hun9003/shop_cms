package com.rateye.shop_cms.infrastructure.mail;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.domain.mail.Mail;
import com.rateye.shop_cms.domain.mail.MailReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailReaderImpl implements MailReader {
    private final MailRepository mailRepository;

    @Override
    public Mail getMailByEmail(String email) {
        return mailRepository.findByEmail(email).orElseThrow(InvalidParamException::new);
    }
}
