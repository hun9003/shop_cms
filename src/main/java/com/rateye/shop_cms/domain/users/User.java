package com.rateye.shop_cms.domain.users;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.domain.AbstractEntity;

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
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Builder
    public User(String email, String name, String password, List<String> roles) {
        if (StringUtils.isEmpty(email)) throw new InvalidParamException("empty email");
        if (StringUtils.isEmpty(name)) throw new InvalidParamException("empty name");
        if (StringUtils.isEmpty(password)) throw new InvalidParamException("empty password");

        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
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
