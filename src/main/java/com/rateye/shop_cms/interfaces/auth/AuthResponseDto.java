package com.rateye.shop_cms.interfaces.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rateye.shop_cms.common.util.PermissionGenerator;
import com.rateye.shop_cms.domain.users.UserInfo;
import com.rateye.shop_cms.domain.users.address.Address;
import com.rateye.shop_cms.domain.users.token.TokenInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class AuthResponseDto {
    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class TokenInfoResponse {
        private final String token;
        private final String[] permissions;

        public TokenInfoResponse(TokenInfo tokenInfo) {
            this.token = tokenInfo.getAccessToken();
            this.permissions = PermissionGenerator.getPermissions(tokenInfo.getRoles());
        }
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Me {
        private final Long id;
        private final String email;
        private final String name;
        private final List<PermissionInfo> permissions;
        private final int active;
        private final Long shopId;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final AuthResponseDto.ProfileInfo profile;
        private final List<AuthResponseDto.AddressInfo> address;

        public Me(UserInfo.Me userInfo, ProfileInfo profile, List<AuthResponseDto.AddressInfo> address) {
            this.id = userInfo.getId();
            this.email = userInfo.getEmail();
            this.name = userInfo.getName();
            this.active = userInfo.getActive();
            this.shopId = userInfo.getShopId();
            this.createdAt = userInfo.getCreatedAt();
            this.updatedAt = userInfo.getUpdatedAt();
            this.profile = profile;
            this.address = address;
            var permissionList = PermissionGenerator.getPermissions(userInfo.getRoles());
            AtomicLong permissionId = new AtomicLong(1L);
            this.permissions = Arrays.stream(permissionList).map(s -> new PermissionInfo(permissionId.getAndIncrement(), s, userInfo.getCreatedAt(), userInfo.getUpdatedAt())).collect(Collectors.toList());
        }

    }

    @Getter
    @ToString
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class AddressInfo {
        private final Long id;
        private final Long userId;
        @JsonProperty(value="default")
        private final int isDefault;
        private final String title;
        private final Address.AddressType type;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final AuthResponseDto.AddressDetailInfo address;
        private final String email;

    }

    @Getter
    @ToString
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class AddressDetailInfo {
        private final String city;
        private final String country;
        private final String state;
        private final String streetAddress;
        private final String zip;
    }

    @Getter
    @ToString
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class ProfileInfo {
        private final String avatar;
        private final String bio;
        private final String contact;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final String email;
        private final String socials;
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class PermissionInfo {
        private final Long id;
        private final String name;
        private final String guardName;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final PivotInfo pivot;

        public PermissionInfo(Long id, String name, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
            this.id = id;
            this.name = name;
            this.guardName = "api";
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.pivot = PivotInfo.builder()
                            .modelId(id)
                            .permissionId(id)
                            .modelType("Marvel\\Database\\Models\\User")
                            .build();

        }
    }

    @Getter
    @ToString
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class PivotInfo {
        private Long modelId;
        private Long permissionId;
        private String modelType;
    }
}
