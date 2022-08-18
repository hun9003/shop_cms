package com.rateye.shop_cms.interfaces.users;

import com.rateye.shop_cms.application.users.UserFacade;
import com.rateye.shop_cms.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
@Api(tags = {"유저 API"})
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserFacade userFacade;

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원 가입", notes = "전달 받은 정보로 회원 가입을 진행 합니다.")
    @ApiResponse(code = 200, message = "성공 시 회원 정보를 반환 합니다")
    public CommonResponse<?> registerMember(@RequestBody @Valid UserRequestDto.SignUpRequest request) {
        var command = request.toCommand();
        var userInfo = userFacade.registerUser(command);
        var response = new UserResponseDto.RegisterMemberResponse(userInfo);
        return CommonResponse.success(response, "가입을 완료 했습니다.");
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호로 로그인을 진행 합니다.")
    @ApiResponse(code = 200, message = "성공 시 회원의 토큰 정보를 반환 합니다")
    public CommonResponse<?> loginMember(@RequestBody @Valid UserRequestDto.LoginRequest request) {
        var criteria = request.toCriteria();
        var tokenInfo = userFacade.loginUser(criteria);
        var response = new UserResponseDto.TokenInfoResponse(tokenInfo);
        return CommonResponse.success(response, "로그인을 완료 했습니다.");
    }

    @PostMapping("/reissue")
    @ApiOperation(value = "토큰 정보 갱신", notes = "refreshToken 으로 accessToken의 유효시간을 갱신 합니다.")
    @ApiResponse(code = 200, message = "성공 시 회원의 토큰정보를 반환 합니다")
    public CommonResponse<?> reissue(@RequestBody @Valid UserRequestDto.ReissueRequest request) {
        var criteria = request.toCriteria();
        var tokenInfo = userFacade.reissueToken(criteria);
        var response = new UserResponseDto.TokenInfoResponse(tokenInfo);
        return CommonResponse.success(response, "토큰 정보가 갱신되었습니다.");
    }

    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "회원의 토큰 정보를 삭제 합니다")
    @ApiResponse(code = 200, message = "성공시 완료 메시지를 반환 합니다.")
    public CommonResponse<?> logoutMember(@RequestBody @Valid UserRequestDto.LogoutRequest request) {
        var criteria = request.toCriteria();
        userFacade.logoutUser(criteria);
        return CommonResponse.success("로그아웃이 완료되었습니다.");
    }
}
