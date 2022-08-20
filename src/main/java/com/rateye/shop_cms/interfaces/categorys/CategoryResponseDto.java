package com.rateye.shop_cms.interfaces.categorys;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class CategoryResponseDto {

    @Getter
    @Builder
    @ToString
    public static class CategoryInfo {
        private final Long id;
        private final String name;
        private final int order;
        private final Long parentId;
        private final List<CategoryInfo> categoryList;
    }
}
