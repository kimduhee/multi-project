package com.framework.common.security.controller;

import com.framework.common.exception.BizException;
import com.framework.common.handler.CommonApiResponse;
import com.framework.common.security.controller.dto.LogOutCOutDto;
import com.framework.common.security.controller.dto.LoginCInDto;
import com.framework.common.security.controller.dto.RefreshTokenCInDto;
import com.framework.common.security.dto.JWTInfo;
import com.framework.common.security.dto.LoginJwtTokenDto;
import com.framework.common.security.dto.UserInfo;
import com.framework.common.security.service.UserInfoService;
import com.framework.common.security.util.JwtUtil;
import com.framework.common.security.util.SecuritySessionUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인 API", description = "로그인 API")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserInfoService userInfoService;

    //access-token 만료시간
    @Value("${jwt.expiration.access-token.time}")
    private String accessTokenexpirationTime;

    //refresh-token 만료시간
    @Value("${jwt.expiration.refresh-token.time}")
    private String refreshTokenexpirationTime;

    //JWT 토큰 키
    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * 로그인 처리(swagger 노출을 위한 더미로그인으로 '/login'은 security에서 처리한다.)
     * @param cInDto
     * @return
     */
    @Operation(summary="로그인", description = "더미 로그인")
    @PostMapping(value="/login")
    public String dummyLogin(@RequestBody LoginCInDto cInDto) {
        return "";
    }

    /**
     * 로그아웃 처리
     * @return
     */
    @Operation(summary="로그아웃", description = "로그아웃")
    @PostMapping(value="/logOut")
    public ResponseEntity<CommonApiResponse> logOut() {

        LogOutCOutDto cOutDto = new LogOutCOutDto();

        UserInfo userInfo = SecuritySessionUtil.getUserInfo();
        JWTInfo updateJWT = new JWTInfo();
        updateJWT.setUserId(userInfo.getUserId());
        updateJWT.setUserAccessToken("logout");
        updateJWT.setUserRefreshToken("logout");
        userInfoService.updateUserToken(updateJWT);
        cOutDto.setResult("success");
        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }

    /**
     * 토큰 재발급
     * @return
     */
    @Operation(summary="토큰 재발급", description = "토큰 재발급")
    @PostMapping(value="/refresh-token")
    public ResponseEntity<CommonApiResponse> refreshToken(@RequestBody RefreshTokenCInDto cInDto) {

        LoginJwtTokenDto jwtTokenDto = new LoginJwtTokenDto();

        //request refresh token
        String token = StringUtils.defaultString(cInDto.getUserRefreshToken());

        //refresh token validation
        if(!"00".equals(JwtUtil.validateToken(token, secretKey))) {
            throw new BizException("ERRCOMLO000004");
        }

        JwtUtil.getUserId(token, secretKey);
        String userId =  JwtUtil.getUserId(token, secretKey);
        JWTInfo jWTInfo = userInfoService.getUserToken(String.valueOf(userId));

        //로그아웃으로 토큰 삭제 상태
        if("logout".equals(jWTInfo.getUserRefreshToken())) {
            throw new BizException("ERRCOMLO000005");
        }

        //request refresh token != db refresh token
        if(!token.equals(jWTInfo.getUserRefreshToken())) {
            throw new BizException("ERRCOMLO000006");
        }

        //access Token 생성
        String accessToken = JwtUtil.createJWT(String.valueOf(userId), secretKey, accessTokenexpirationTime);
        //refresh Token 생성
        String refreshToken = JwtUtil.createJWT(String.valueOf(userId), secretKey, refreshTokenexpirationTime);

        JWTInfo updateJWT = new JWTInfo();
        updateJWT.setUserId(Integer.parseInt(userId));
        updateJWT.setUserAccessToken(accessToken);
        updateJWT.setUserRefreshToken(refreshToken);
        userInfoService.updateUserToken(updateJWT);

        jwtTokenDto.setAccessToken(accessToken);
        jwtTokenDto.setRefreshToken(refreshToken);

        return new ResponseEntity<>(CommonApiResponse.ok(jwtTokenDto), HttpStatus.OK);
    }
}
