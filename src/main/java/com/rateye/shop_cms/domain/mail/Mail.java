package com.rateye.shop_cms.domain.mail;

import com.rateye.shop_cms.common.util.redis.TokenGenerator;
import com.rateye.shop_cms.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name="mail")
public class Mail extends AbstractEntity {
    @Id
    private String email;

    @Column
    private String code;

    @Builder
    public Mail(String email) {
        this.email = email;
        this.code = TokenGenerator.randomCharacter(6);
    }
}
