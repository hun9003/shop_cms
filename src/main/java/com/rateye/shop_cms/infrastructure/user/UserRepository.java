package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);
    boolean existsByIdAndPassword(String id, String password);
}
