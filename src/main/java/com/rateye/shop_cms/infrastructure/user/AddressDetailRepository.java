package com.rateye.shop_cms.infrastructure.user;

import com.rateye.shop_cms.domain.users.address.AddressDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDetailRepository extends JpaRepository<AddressDetail, Long> {
    AddressDetail findByAddress_Id(Long addressId);
}
