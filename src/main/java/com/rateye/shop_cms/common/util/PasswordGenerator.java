package com.rateye.shop_cms.common.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    // 비밀번호 암호화
    public static String passwordEncoder(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    // 비밀번호 확인
    public static boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
