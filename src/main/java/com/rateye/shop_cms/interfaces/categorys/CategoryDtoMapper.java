package com.rateye.shop_cms.interfaces.categorys;

import com.rateye.shop_cms.domain.category.CategoryInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CategoryDtoMapper {

    CategoryResponseDto.CategoryInfo of(CategoryInfo categoryInfo);
}