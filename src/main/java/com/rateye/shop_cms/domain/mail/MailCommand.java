package com.rateye.shop_cms.domain.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MailCommand {
    @Getter
    @Builder
    @ToString
    public static class ForgetPasswordRequest {
        private final String email;

        public Mail toEntity() {
            return Mail.builder()
                    .email(email)
                    .build();
        }
    }
}
