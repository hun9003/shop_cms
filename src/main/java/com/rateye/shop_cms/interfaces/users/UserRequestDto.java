package com.rateye.shop_cms.interfaces.users;

import com.rateye.shop_cms.common.util.PasswordGenerator;
import com.rateye.shop_cms.domain.users.UserCommand;
import com.rateye.shop_cms.domain.users.UserCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRequestDto {
    @Getter
    @Setter
    @ApiModel(value = "회원 가입 요청", description = "회원 가입에 필요한 정보 입니다.")
    public static class SignUpRequest {
        @NotEmpty(message = "아이디는 필수 값 입니다.")
        @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$", message = "아이디는 6~12자 영문, 숫자를 사용 하세요.")
        @ApiModelProperty(name = "id", example = "test12345", notes = "아이디는 6~12자 영문, 숫자를 입력 받습니다.", required = true)
        private String id;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @ApiModelProperty(name = "password", example = "abcd1234!@", notes = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String password;

        public UserCommand.RegisterUserRequest toCommand() {
            return UserCommand.RegisterUserRequest.builder()
                    .id(id)
                    .password(PasswordGenerator.passwordEncoder(password))
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "로그인 요청", description = "로그인에 필요한 정보 입니다.")
    public static class LoginRequest {
        @NotEmpty(message = "아이디는 필수 값 입니다.")
        @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$", message = "아이디는 6~12자 영문, 숫자를 사용 하세요.")
        @ApiModelProperty(name = "id", example = "test12345", notes = "아이디는 6~12자 영문, 숫자를 입력 받습니다.", required = true)
        private String id;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @ApiModelProperty(name = "password", example = "abcd1234!@", notes = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String password;

        public UserCriteria.LoginUserRequest toCriteria() {
            return UserCriteria.LoginUserRequest.builder()
                    .id(id)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "토큰 갱신 요청 정보", description = "토큰 갱신에 필요한 정보 입니다.")
    public static class ReissueRequest {
        @NotEmpty(message = "accessToken 은 필수 값 입니다.")
        @ApiModelProperty(name = "accessToken",
                example = "access_token",
                notes = "회원의 엑세스 토큰 입니다.")
        private String accessToken;

        @NotEmpty(message = "refreshToken 은 필수 값 입니다.")
        @ApiModelProperty(name = "accessToken",
                example = "refresh_token",
                notes = "회원의 리프레시 토큰 입니다.")
        private String refreshToken;

        public UserCriteria.ReissueTokenRequest toCriteria() {
            return UserCriteria.ReissueTokenRequest.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "로그아웃 요청 정보", description = "로그아웃에 필요한 정보 입니다.")
    public static class LogoutRequest {
        @NotEmpty(message = "잘못된 요청 입니다.")
        @ApiModelProperty(name = "accessToken",
                example = "access_token",
                notes = "회원의 엑세스 토큰 입니다.")
        private String accessToken;

        @NotEmpty(message = "잘못된 요청 입니다.")
        @ApiModelProperty(name = "refreshToken",
                example = "refresh_token",
                notes = "회원의 리프레시 토큰 입니다.")
        private String refreshToken;

        public UserCriteria.LogoutRequest toCriteria() {
            return UserCriteria.LogoutRequest.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }
}
