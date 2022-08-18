package com.rateye.shop_cms.domain.category;

public interface CategoryStore {
    Category save(Category initCategory);
    void delete(Category initCategory);
}
