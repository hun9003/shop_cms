package com.rateye.shop_cms.domain.category;

import java.util.List;

public interface CategoryService {
    CategoryInfo createCategory(CategoryCommand.CategoryCreateRequest command);
    CategoryInfo updateCategory(CategoryCommand.CategoryUpdateRequest command);
    void deleteCategory(Long categoryId);
    List<CategoryInfo> getCategoryList(Long parentId);
}
