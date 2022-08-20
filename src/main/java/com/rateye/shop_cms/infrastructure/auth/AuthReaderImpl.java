package com.rateye.shop_cms.infrastructure.auth;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.domain.users.User;
import com.rateye.shop_cms.domain.auth.AuthCriteria;
import com.rateye.shop_cms.domain.auth.AuthReader;
import com.rateye.shop_cms.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthReaderImpl implements AuthReader {
    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findById(email).orElseThrow(InvalidParamException::new);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean loginUser(AuthCriteria.LoginRequest criteria) {
        return userRepository.existsByEmailAndPassword(criteria.getEmail(), criteria.getPassword());
    }
}
