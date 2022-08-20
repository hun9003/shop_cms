package com.rateye.shop_cms.domain.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final MailStore mailStore;
    private final String FROM_ADDRESS = "jinhun3892@gmail.com";

    @Override
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
}
