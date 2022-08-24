package com.rateye.shop_cms.domain.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MailCriteria {

    @Getter
    @Builder
    @ToString
    public static class VerifyForgetPasswordRequest {
        private final String email;
        private final String token;
    }

}
