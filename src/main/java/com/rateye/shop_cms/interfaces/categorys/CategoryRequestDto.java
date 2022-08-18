package com.rateye.shop_cms.interfaces.categorys;

import com.rateye.shop_cms.domain.category.CategoryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

public class CategoryRequestDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "카테고리 생성 요청", description = "카테고리 생성에 필요한 정보 입니다.")
    public static class CategoryCreateRequest {
        @NotEmpty(message = "카테고리 이름은 필수 값 입니다.")
        @ApiModelProperty(name = "name", example = "맨투맨", notes = "카테고리 이름 입니다.", required = true)
        private final String name;
        @ApiModelProperty(name = "parent", example = "1", notes = "카테고리 부모 IDX 입니다.")
        private final Long parent;

        public CategoryCommand.CategoryCreateRequest toCommand() {
            return CategoryCommand.CategoryCreateRequest.builder()
                    .name(name)
                    .parent(parent)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "카테고리 수정 요청", description = "카테고리 수정에 필요한 정보 입니다.")
    public static class CategoryUpdateRequest {
        @NotEmpty(message = "카테고리 이름은 필수 값 입니다.")
        @ApiModelProperty(name = "name", example = "맨투맨", notes = "카테고리 이름 입니다.", required = true)
        private final String name;

        @ApiModelProperty(name = "parent", example = "1", notes = "카테고리 부모 IDX 입니다.")
        private final Long parent;

        public CategoryCommand.CategoryUpdateRequest toCommand(Long id) {
            return CategoryCommand.CategoryUpdateRequest.builder()
                    .id(id)
                    .name(name)
                    .parent(parent)
                    .build();
        }
    }
}
