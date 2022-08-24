package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.domain.users.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser_Email(String email);
}