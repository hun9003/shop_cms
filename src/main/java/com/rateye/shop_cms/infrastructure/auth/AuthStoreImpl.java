package com.rateye.shop_cms.infrastructure.auth;

import com.rateye.shop_cms.domain.users.User;
import com.rateye.shop_cms.domain.auth.AuthStore;
import com.rateye.shop_cms.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthStoreImpl implements AuthStore {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
