package com.rateye.shop_cms.domain.users.address;

import com.rateye.shop_cms.domain.AbstractEntity;
import com.rateye.shop_cms.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name="address")
public class Address extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    @Column(name = "is_default")
    private boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @Column(name = "address_title")
    private String addressTitle;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private AddressDetail addressDetail;

    @Getter
    @AllArgsConstructor
    public enum AddressType {
        BILLING("주소"),
        SHIPPING("배송지");

        private final String description;
    }

    public Address(User user, boolean isDefault, AddressType addressType, AddressDetail addressDetail) {
        this.user = user;
        this.isDefault = isDefault;
        this.addressType = addressType;
        this.addressTitle = addressType.description;
        this.addressDetail = addressDetail;
    }
}
