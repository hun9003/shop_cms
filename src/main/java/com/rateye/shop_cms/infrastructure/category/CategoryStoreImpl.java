package com.rateye.shop_cms.infrastructure.category;

import com.rateye.shop_cms.domain.category.Category;
import com.rateye.shop_cms.domain.category.CategoryStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryStoreImpl implements CategoryStore {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category initCategory) {
        return categoryRepository.save(initCategory);
    }

    @Override
    public void delete(Category initCategory) {
        categoryRepository.delete(initCategory);
    }
}