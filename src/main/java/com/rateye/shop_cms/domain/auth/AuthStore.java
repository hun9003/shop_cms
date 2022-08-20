package com.rateye.shop_cms.domain.auth;

import com.rateye.shop_cms.domain.users.User;

public interface AuthStore {
    User save(User user);
}
