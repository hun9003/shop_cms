package com.rateye.shop_cms.domain.users;

public interface UserReader {
    User getUserById(String id);
    boolean existsUserById(String id);
    boolean loginUser(UserCriteria.LoginUserRequest criteria);
}
