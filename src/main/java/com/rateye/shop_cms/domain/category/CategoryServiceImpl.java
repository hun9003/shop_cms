package com.rateye.shop_cms.domain.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryStore categoryStore;
    private final CategoryReader categoryReader;

    /**
     * 카테고리 생성
     * @param command 카테고리 data
     * @return 생성된 카테고리 정보
     */
    @Override
    @Transactional
    public CategoryInfo createCategory(CategoryCommand.CategoryCreateRequest command) {
        System.out.println("CategoryServiceImpl :: createCategory");

        Category parentCategory = null;
        if (command.getParent() != null) { // 기준이 되는 카테고리가 있으면 호출하기
            parentCategory = categoryReader.getCategory(command.getParent());
        }

        // 정렬 순서를 저장하기 위해 카테고리 개수 가져오기
        var order = categoryReader.getCategoryCount(parentCategory) + 1;

        // 생성할 카테고리 데이터
        var initCategory = command.toEntity(order, parentCategory);

        // 카테고리 저장
        var category = categoryStore.save(initCategory);

        return new CategoryInfo(category, null);
    }

    /**
     * 카테고리 수정
     * @param command 카테고리 data
     * @return 수정된 카테고리 정보
     */
    @Override
    @Transactional
    public CategoryInfo updateCategory(CategoryCommand.CategoryUpdateRequest command) {
        // 수정할 카테고리 데이터
        var initCategory = categoryReader.getCategory(command.getId());

        // 카테고리의 이름 수정
        initCategory.setName(command.getName());

        // 카테고리의 부모 수정
        if (command.getParent() != null) {
            initCategory.setParent(categoryReader.getCategory(command.getParent()));
        } else {
            initCategory.setParent(null);
        }

        // 수정한 데이터 저장
        var category = categoryStore.save(initCategory);

        // 하위 카테고리 가져오기
        var childCategoryList = childCategoryFactory(category);
        return new CategoryInfo(category, childCategoryList);
    }

    /**
     * 카테고리 삭제
     * @param command 카테고리 data
     */
    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        // 삭제할 카테고리 데이터
        var initCategory = categoryReader.getCategory(categoryId);

        // 삭제
        categoryStore.delete(initCategory);
    }

    /**
     * 카테고리 리스트 호출
     * @param parentId 기준이 되는 카테고리 부모 (없으면 전체 카테고리)
     * @return 호출한 카테고리 리스트
     */
    @Override
    @Transactional
    public List<CategoryInfo> getCategoryList(Long parentId) {
        Category parent = null;
        if (parentId != null) { // 기준이 되는 카테고리가 있으면 호출하기
            parent = categoryReader.getCategory(parentId);
        }

        // 카테고리 리스트 가져오기
        var categoryList = categoryReader.getCategoryList(parent);

        // 하위 카테고리들을 세팅 후 그대로 리턴
        return categoryList.stream().map(category -> {
            var childCategoryList = childCategoryFactory(category);
            return new CategoryInfo(category, childCategoryList);
        }).collect(Collectors.toList());
    }

    /**
     * 하위 카테고리 생성하는 메서드
     * @param category 기준이 되는 카테고리
     * @return 생성된 하위 카테고리들
     */
    private List<CategoryInfo> childCategoryFactory(Category category) {
        return category.getCategoryList().stream().map(childCategory -> {
            if (childCategory.getCategoryList().size() != 0) {
                return new CategoryInfo(childCategory, childCategoryFactory(childCategory));
            } else {
                return new CategoryInfo(childCategory, null);
            }
        }).collect(Collectors.toList());
    }
}
