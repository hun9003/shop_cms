package com.rateye.shop_cms.infrastructure.auth;

import com.rateye.shop_cms.domain.users.User;
import com.rateye.shop_cms.domain.auth.AuthStore;
import com.rateye.shop_cms.domain.users.profile.Profile;
import com.rateye.shop_cms.infrastructure.user.ProfileRepository;
import com.rateye.shop_cms.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthStoreImpl implements AuthStore {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public User save(User user) {
        Profile profile = user.getProfile();
        if (profile == null) {
            profile = new Profile(user);
        }
        user.setProfile(profile);
        var save = userRepository.save(user);
        profileRepository.save(profile);
        return save;
    }
}
