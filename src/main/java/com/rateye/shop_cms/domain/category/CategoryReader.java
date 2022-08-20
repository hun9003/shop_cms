package com.rateye.shop_cms.domain.category;

import java.util.List;

public interface CategoryReader {
    int getCategoryCount(Category category);
    Category getCategory(Long id);
    List<Category> getCategoryList(Category parent);

    boolean existsById(Long id);
}
