package com.rateye.shop_cms.domain.mail;

import com.rateye.shop_cms.domain.auth.AuthCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "jinhun3892@gmail.com";

    @Override
    public void forgetPassword(AuthCommand.ForgetPasswordRequest command) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(command.getEmail());
        message.setFrom(FROM_ADDRESS);
        message.setSubject("인증 코드 입니다.");
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}
