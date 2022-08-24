package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
