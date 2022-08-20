package com.rateye.shop_cms.interfaces.auth;

import com.rateye.shop_cms.application.auth.AuthFacade;
import com.rateye.shop_cms.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"인증 API"})
@RequestMapping("/api/v1/auth")
public class AuthApiController {
    private final AuthFacade authFacade;

    @PostMapping("/register")
    @ApiOperation(value = "회원 가입", notes = "전달 받은 정보로 회원 가입을 진행 합니다.")
    public CommonResponse<?> register(@RequestBody @Valid AuthRequestDto.RegisterRequest request) {
        var command = request.toCommand();
        var tokenInfo = authFacade.register(command);
        var response = new AuthResponseDto.TokenInfoResponse(tokenInfo);
        return CommonResponse.success(response, "가입을 완료 했습니다.");
    }

    @PostMapping("/token")
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호로 로그인을 진행 합니다.")
    public CommonResponse<?> loginMember(@RequestBody @Valid AuthRequestDto.LoginRequest request) {
        var criteria = request.toCriteria();
        var tokenInfo = authFacade.login(criteria);
        var response = new AuthResponseDto.TokenInfoResponse(tokenInfo);
        return CommonResponse.success(response, "로그인을 완료 했습니다.");
    }

    @PostMapping("/forget-password")
    @ApiOperation(value = "비밀번호 변경 이메일 토큰 전송", notes = "비밀번호 변경을 위한 이메일 토큰을 이메일로 전송 합니다.")
    public CommonResponse<?> forgetPassword(@RequestBody @Valid AuthRequestDto.ForgetPasswordRequest request) {
        var command = request.toCommand();
        return CommonResponse.success(command.getEmail()+" 메일로 토큰을 전송 했습니다.");
    }

    @PostMapping("/verify-forget-password-token")
    @ApiOperation(value = "비밀번호 변경 이메일 토큰 검증", notes = "비밀번호 변경을 위한 이메일 토큰을 검증 합니다.")
    public CommonResponse<?> verifyForgetPassword() {
        return CommonResponse.success("OK");
    }

    @PostMapping("/reset-password")
    @ApiOperation(value = "비밀번호 리셋", notes = "비밀번호를 리셋 합니다.")
    public CommonResponse<?> resetPassword() {
        return CommonResponse.success("OK");
    }

    @PostMapping("/change-password")
    @ApiOperation(value = "비밀번호 재설정", notes = "비밀번호를 재설정 합니다.")
    public CommonResponse<?> changePassword() {
        return CommonResponse.success("OK");
    }
}
