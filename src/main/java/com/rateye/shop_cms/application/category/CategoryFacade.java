package com.rateye.shop_cms.application.category;

import com.rateye.shop_cms.domain.category.CategoryCommand;
import com.rateye.shop_cms.domain.category.CategoryInfo;
import com.rateye.shop_cms.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService categoryService;

    public List<CategoryInfo> findCategoryInfoList(Long parentId) {
        return categoryService.getCategoryList(parentId);
    }

    public CategoryInfo createCategory(CategoryCommand.CategoryCreateRequest command) {
        return categoryService.createCategory(command);
    }

    public CategoryInfo updateCategory(CategoryCommand.CategoryUpdateRequest command) {
        return categoryService.updateCategory(command);
    }

    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

}