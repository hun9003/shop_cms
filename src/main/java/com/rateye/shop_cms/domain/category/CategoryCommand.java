package com.rateye.shop_cms.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CategoryCommand {

    @Getter
    @Builder
    @ToString
    public static class CategoryCreateRequest {
        private final String name;
        private final Long parent;

        public Category toEntity(int order, Category parent) {
            return Category.builder()
                    .name(name)
                    .parent(parent)
                    .order(order)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class CategoryUpdateRequest {
        private final Long id;
        private final String name;
        private final Long parent;
    }

    @Getter
    @Builder
    @ToString
    public static class CategoryDeleteRequest {
        private final Long id;
    }
}
