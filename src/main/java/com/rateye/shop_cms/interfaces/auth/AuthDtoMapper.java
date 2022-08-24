package com.rateye.shop_cms.interfaces.auth;

import com.rateye.shop_cms.domain.users.UserInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuthDtoMapper {
    AuthResponseDto.ProfileInfo of(UserInfo.ProfileInfo profileInfo);
    AuthResponseDto.AddressDetailInfo of(UserInfo.AddressDetailInfo addressDetailInfo);
    AuthResponseDto.AddressInfo of(UserInfo.AddressInfo addressInfo);
}
