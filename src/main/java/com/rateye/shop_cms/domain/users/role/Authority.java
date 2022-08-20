package com.rateye.shop_cms.domain.users.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {
    CUSTOMER("Customer"),
    STAFF("Staff"),
    STORE_OWNER("Store owner"),
    SUPER_ADMIN("Super admin");

    private final String description;

    public String getDescription(Object... arg) {
        return String.format(description, arg);
    }
}
