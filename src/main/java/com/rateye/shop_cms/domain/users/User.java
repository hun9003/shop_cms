package com.rateye.shop_cms.domain.users;

import com.google.common.collect.Lists;
import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.common.util.PasswordGenerator;
import com.rateye.shop_cms.domain.AbstractEntity;
import com.rateye.shop_cms.domain.users.address.Address;
import com.rateye.shop_cms.domain.users.profile.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="users")
public class User extends AbstractEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Address> address = Lists.newArrayList();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Builder
    public User(Long id, String email, String name, String password, Long shopId, boolean isActive, List<Address> address, Profile profile, List<String> roles) {
        if (StringUtils.isEmpty(email)) throw new InvalidParamException("empty email");
        if (StringUtils.isEmpty(name)) throw new InvalidParamException("empty name");
        if (StringUtils.isEmpty(password)) throw new InvalidParamException("empty password");
        this.id = null;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.shopId = shopId;
        this.isActive = isActive;
        this.profile = profile;
        this.address = address;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPassword(String password) {
        this.password = PasswordGenerator.passwordEncoder(password);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
