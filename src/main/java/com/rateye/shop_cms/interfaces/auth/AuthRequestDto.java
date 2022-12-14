package com.rateye.shop_cms.interfaces.auth;

import com.rateye.shop_cms.domain.auth.AuthCommand;
import com.rateye.shop_cms.domain.auth.AuthCriteria;
import com.rateye.shop_cms.domain.mail.MailCommand;
import com.rateye.shop_cms.domain.mail.MailCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Locale;

public class AuthRequestDto {

    @Getter
    @Setter
    @ApiModel(value = "회원 가입 요청", description = "회원 가입에 필요한 정보 입니다.")
    public static class RegisterRequest {
        @NotEmpty(message = "이메일은 필수 값 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        @ApiModelProperty(name = "email", example = "test12345@example.com", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String email;

        @NotEmpty(message = "이름은 필수 값 입니다.")
        @ApiModelProperty(name = "name", example = "홍길동", notes = "사용자의 이름 입니다.", required = true)
        private String name;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @ApiModelProperty(name = "password", example = "abcd1234!@", notes = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String password;

        @NotEmpty(message = "권한은 필수 값 입니다.")
        @ApiModelProperty(name = "permission", example = "store_owner", notes = "회원 가입 한 유저의 권한 입니다..", required = true)
        private String permission;

        public AuthCommand.RegisterRequest toCommand() {
            String role = permission.toUpperCase(Locale.ROOT).replace(" ", "_");
            return AuthCommand.RegisterRequest.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .role(role)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "로그인 요청", description = "로그인에 필요한 정보 입니다.")
    public static class LoginRequest {
        @NotEmpty(message = "이메일은 필수 값 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        @ApiModelProperty(name = "email", example = "test12345@example.com", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String email;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @ApiModelProperty(name = "password", example = "abcd1234!@", notes = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String password;

        public AuthCriteria.LoginRequest toCriteria() {
            return AuthCriteria.LoginRequest.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "이메일 토큰 전송 요청", description = "이메일 토큰 전송에 필요한 정보 입니다.")
    public static class ForgetPasswordRequest {
        @NotEmpty(message = "이메일은 필수 값 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        @ApiModelProperty(name = "email", example = "test12345@example.com", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String email;

        public MailCommand.ForgetPasswordRequest toCommand() {
            return MailCommand.ForgetPasswordRequest.builder()
                    .email(email)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "이메일 토큰 검증 요청", description = "이메일 토큰 검증에 필요한 정보 입니다.")
    public static class VerifyForgetPasswordRequest {
        @NotEmpty(message = "이메일은 필수 값 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        @ApiModelProperty(name = "email", example = "test12345@example.com", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String email;

        @NotEmpty(message = "토큰은 필수 값 입니다.")
        @Pattern(regexp = "[A-Za-z0-9]{6}$", message = "토큰 형식에 맟지 않습니다.")
        @ApiModelProperty(name = "token", example = "e1Ym3O", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String token;

        public MailCriteria.VerifyForgetPasswordRequest toCriteria() {
            return MailCriteria.VerifyForgetPasswordRequest.builder()
                    .email(email)
                    .token(token)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "비밀번호 리셋 요청", description = "비밀번호 리셋에 필요한 정보 입니다.")
    public static class ResetPasswordRequest {
        @NotEmpty(message = "이메일은 필수 값 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        @ApiModelProperty(name = "email", example = "test12345@example.com", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String email;

        @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
        @ApiModelProperty(name = "password", example = "abcd1234!@", notes = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String password;

        @NotEmpty(message = "토큰은 필수 값 입니다.")
        @Pattern(regexp = "[A-Za-z0-9]{6}$", message = "토큰 형식에 맟지 않습니다.")
        @ApiModelProperty(name = "token", example = "e1Ym3O", notes = "이메일 형식에 맞게 입력 받습니다.", required = true)
        private String token;


        public AuthCommand.ResetPasswordRequest toCommand() {
            return AuthCommand.ResetPasswordRequest.builder()
                    .email(email)
                    .password(password)
                    .token(token)
                    .build();
        }
    }

    @Getter
    @Setter
    @ApiModel(value = "비밀번호 재설정 요청", description = "비밀번호 재설정에 필요한 정보 입니다.")
    public static class ChangePassword {
        @NotEmpty(message = "새 비밀번호는 필수 입력값입니다.")
        @ApiModelProperty(name = "password", example = "newPassword12!@", notes = "새 비밀번호 입니다 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String newPassword;

        @NotEmpty(message = "예전 비밀번호는 필수 입력값입니다.")
        @ApiModelProperty(name = "password", example = "oldPassword12!@", notes = "변경 전 비밀번호 입니다 8~16자 영문 대 소문자, 숫자, 특수문자를 입력 받습니다.", required = true)
        private String oldPassword;

        public AuthCommand.ChangePassword toCommand() {
            return new AuthCommand.ChangePassword(newPassword, oldPassword);
        }
    }
}
