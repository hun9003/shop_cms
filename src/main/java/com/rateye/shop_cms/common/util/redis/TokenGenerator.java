package com.rateye.shop_cms.common.util.redis;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 32;

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
    }
}