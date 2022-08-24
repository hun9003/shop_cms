package com.rateye.shop_cms.domain.users;

import com.rateye.shop_cms.domain.users.address.Address;
import com.rateye.shop_cms.domain.users.address.AddressDetail;
import com.rateye.shop_cms.domain.users.profile.Profile;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;


public class UserInfo {
    @Getter
    @ToString
    public static class Main {
        private final String email;
        private final String name;
        private final List<String> roles;

        public Main(User user) {
            this.email = user.getEmail();
            this.name = user.getName();
            this.roles = user.getRoles();
        }
    }

    @Getter
    @ToString
    public static class Me {
        private final Long id;
        private final String email;
        private final String name;
        private final List<String> roles;
        private final int active;
        private final Long shopId;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final ProfileInfo profile;
        private final List<AddressInfo> address;

        public Me(User user, List<AddressInfo> address, ProfileInfo profile) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.roles = user.getRoles();
            this.active = user.isActive() ? 1 : 0;
            this.shopId = user.getShopId();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
            this.profile = profile;
            this.address = address;
        }
    }

    @Getter
    @ToString
    public static class AddressInfo {
        private final Long id;
        private final Long userId;
        private final int isDefault;
        private final String title;
        private final Address.AddressType type;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final AddressDetailInfo address;
        private final String email;

        public AddressInfo(Address address, AddressDetailInfo addressDetailInfo) {
            this.id = address.getId();
            this.email = address.getUser().getEmail();
            this.userId = address.getUser().getId();
            this.isDefault = address.isDefault() ? 1 : 0;
            this.title = address.getAddressTitle();
            this.type = address.getAddressType();
            this.createdAt = address.getCreatedAt();
            this.updatedAt = address.getUpdatedAt();
            this.address = addressDetailInfo;
        }
    }

    @Getter
    @ToString
    public static class AddressDetailInfo {
        private final String city;
        private final String country;
        private final String state;
        private final String streetAddress;
        private final String zip;

        public AddressDetailInfo(AddressDetail addressDetail) {
            this.city = addressDetail.getCity();
            this.country = addressDetail.getCountry();
            this.state = addressDetail.getState();
            this.streetAddress = addressDetail.getStreetAddress();
            this.zip = addressDetail.getZip();
        }
    }

    @Getter
    @ToString
    public static class ProfileInfo {
        private final Long id;
        private final String avatar;
        private final String bio;
        private final String contact;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;
        private final String email;
        private final String socials;

        public ProfileInfo(Profile profile) {
            this.id = profile.getId();
            this.avatar = profile.getAvatar();
            this.bio = profile.getBio();
            this.contact = profile.getContact();
            this.createdAt = profile.getCreatedAt();
            this.updatedAt = profile.getUpdatedAt();
            this.email = profile.getUser().getEmail();
            this.socials = profile.getSocials();
        }
    }

}
