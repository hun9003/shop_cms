package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.domain.users.User;
import com.rateye.shop_cms.domain.users.UserCriteria;
import com.rateye.shop_cms.domain.users.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(InvalidParamException::new);
    }

    @Override
    public boolean existsUserById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean loginUser(UserCriteria.LoginUserRequest criteria) {
        return userRepository.existsByIdAndPassword(criteria.getId(), criteria.getPassword());
    }
}
