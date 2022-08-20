package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
