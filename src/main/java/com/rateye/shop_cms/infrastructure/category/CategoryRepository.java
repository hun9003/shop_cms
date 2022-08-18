package com.rateye.shop_cms.infrastructure.category;

import com.rateye.shop_cms.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Integer countByParent(Category category);
    List<Category> findAllByParentOrderByOrder(Category Parent);
}