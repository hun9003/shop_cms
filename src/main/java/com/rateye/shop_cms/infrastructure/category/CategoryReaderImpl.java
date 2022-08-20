package com.rateye.shop_cms.infrastructure.category;

import com.rateye.shop_cms.common.exception.InvalidParamException;
import com.rateye.shop_cms.domain.category.Category;
import com.rateye.shop_cms.domain.category.CategoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;


    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(InvalidParamException::new);
    }

    @Override
    public int getCategoryCount(Category category) {
        return categoryRepository.countByParent(category);
    }

    @Override
    public List<Category> getCategoryList(Category Parent) {
        return categoryRepository.findAllByParentOrderByOrder(Parent);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
