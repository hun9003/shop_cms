package com.rateye.shop_cms.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    @ApiModelProperty(value = "Response 성공 여부", example = "true")
    private boolean ok;
    @ApiModelProperty(value = "Response 데이터", example = "plain text 또는 json 형태의 데이터")
    private T data;
    @ApiModelProperty(value = "Response 메시지", example = "성공 안내 메시지")
    private String message;
    @ApiModelProperty(value = "Response 에러 코드", example = "에러 코드")
    private String errorCode;

    public static <T> CommonResponse<T> success(T data, String message) {
        return (CommonResponse<T>) CommonResponse.builder()
                .ok(true)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static CommonResponse fail(String message, String errorCode) {
        return CommonResponse.builder()
                .ok(false)
                .message(message)
                .errorCode(errorCode)
                .build();
    }

    public static CommonResponse fail(ErrorCode errorCode) {
        return CommonResponse.builder()
                .ok(false)
                .message(errorCode.getErrorMsg())
                .errorCode(errorCode.name())
                .build();
    }
}
