package com.rateye.shop_cms.domain.category;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CategoryInfo {
    private final Long id;
    private final String name;
    private final int order;
    private final List<CategoryInfo> categoryList;

    public CategoryInfo(Category category, List<CategoryInfo> categoryList) {
        this.id = category.getId();
        this.name = category.getName();
        this.order = category.getOrder();
        this.categoryList = categoryList;
    }
}
