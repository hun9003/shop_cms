package com.rateye.shop_cms.domain.auth;

import com.rateye.shop_cms.domain.users.User;

public interface AuthReader {
    User getUserByEmail(String email);
    boolean existsUserByEmail(String email);
    boolean loginUser(AuthCriteria.LoginRequest criteria);
}
