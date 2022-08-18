package com.rateye.shop_cms.interfaces.categorys;

import com.rateye.shop_cms.application.category.CategoryFacade;
import com.rateye.shop_cms.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"카테고리 API"})
@RequestMapping("/api/v1/category")
public class CategoryApiController {
    private final CategoryFacade categoryFacade;
    private final CategoryDtoMapper categoryDtoMapper;

    /**
     * 카테고리 리스트 호출
     * @param parentId 기준이 되는 카테고리 부모 (없으면 전체 카테고리)
     * @return 카테고리 리스트
     */
    @GetMapping
    @ApiOperation(value = "카테고리 리스트 호출", notes = "전달 받은 카테고리 IDX로 하위 카테고리 리스트를 호출 합니다. IDX를 전달하지 않으면 전체 리스트를 호출 합니다.")
    @ApiResponse(code = 200, message = "성공 시 카테고리 리스트를 반환 합니다")
    public CommonResponse getCategoryList(@RequestParam(value = "categoryId", required = false) Long parentId) {
        var categoryInfoList = categoryFacade.findCategoryInfoList(parentId);
        var response = categoryInfoList.stream().map(categoryDtoMapper::of);
        return CommonResponse.success(response, "조회 완료");
    }

    /**
     * 카테고리 생성
     * @param request 카테고리 data
     * @return 생성한 카테고리 정보
     */
    @PostMapping
    @ApiOperation(value = "카테고리 생성", notes = "전달 받은 카테고리 정보로 카테고리를 생성 합니다.")
    @ApiResponse(code = 200, message = "성공 시 생성한 카테고리 정보를 반환 합니다")
    public CommonResponse createCategory(@RequestBody @Valid CategoryRequestDto.CategoryCreateRequest request) {
        var command = request.toCommand();
        var categoryInfo = categoryFacade.createCategory(command);
        var response = categoryDtoMapper.of(categoryInfo);
        return CommonResponse.success(response, "생성 완료");
    }

    /**
     * 카테고리 수정
     * @param request 카테고리 data
     * @return 수정한 카테고리 정보
     */
    @PutMapping("/{categoryId}")
    @ApiOperation(value = "카테고리 수정", notes = "전달 받은 카테고리 정보로 카테고리를 수정 합니다.")
    @ApiResponse(code = 200, message = "성공 시 생성한 카테고리 정보를 반환 합니다")
    public CommonResponse updateCategory(@ApiParam(name = "categoryId", example = "1") @PathVariable(name = "categoryId") Long categoryId,
            @RequestBody @Valid CategoryRequestDto.CategoryUpdateRequest request) {
        var command = request.toCommand(categoryId);
        var categoryInfo = categoryFacade.updateCategory(command);
        var response = categoryDtoMapper.of(categoryInfo);
        return CommonResponse.success(response, "수정 완료");
    }

    /**
     * 카테고리 삭제
     * @param categoryId 카테고리 IDX
     * @return
     */
    @DeleteMapping("/{categoryId}")
    @ApiOperation(value = "카테고리 삭제", notes = "전달 받은 카테고리 IDX로 카테고리를 삭제 합니다.")
    @ApiResponse(code = 200, message = "성공 시 생성한 카테고리 정보를 반환 합니다")
    public CommonResponse deleteCategory(@ApiParam(name = "categoryId", example = "1") @PathVariable(name = "categoryId") Long categoryId) {
        categoryFacade.deleteCategory(categoryId);
        return CommonResponse.success("삭제 완료");
    }
}
