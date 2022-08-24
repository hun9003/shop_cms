package com.rateye.shop_cms.domain.mail;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final MailStore mailStore;
    private final MailReader mailReader;
    private final String FROM_ADDRESS = "jinhun3892@gmail.com";

    @Override
    @Transactional
    public void forgetPassword(MailCommand.ForgetPasswordRequest command) {
        var initMail = command.toEntity();
        var mail = mailStore.save(initMail);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(command.getEmail());
        message.setFrom(FROM_ADDRESS);
        message.setSubject("인증 코드 입니다.");
        message.setText("인증 코드 : " + mail.getCode());

        mailSender.send(message);
    }

    @Override
    @Transactional(readOnly = true)
    public void verifyForgetPassword(MailCriteria.VerifyForgetPasswordRequest criteria) {
        var mail = mailReader.getMailByEmail(criteria.getEmail());
        if (!mail.getCode().equals(criteria.getToken())) throw new InvalidParamException(ErrorCode.MAIL_FAIL_INVALID_TOKEN);
    }
}
