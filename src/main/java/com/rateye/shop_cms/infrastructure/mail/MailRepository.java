package com.rateye.shop_cms.infrastructure.mail;

import com.rateye.shop_cms.domain.mail.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {
    Optional<Mail> findByEmail(String email);
}
