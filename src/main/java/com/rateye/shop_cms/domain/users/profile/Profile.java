package com.rateye.shop_cms.domain.users.profile;

import com.rateye.shop_cms.domain.AbstractEntity;
import com.rateye.shop_cms.domain.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name="profile")
public class Profile extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String avatar;

    @Column
    private String bio;

    @Column
    private String contact;

    @Column
    private String socials;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    public Profile(User user) {
        this.user = user;
        user.setProfile(this);
    }
}
