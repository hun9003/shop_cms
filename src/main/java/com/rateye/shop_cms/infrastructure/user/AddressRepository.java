package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.domain.users.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUser_Email(String email);
}
