package com.framework.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.framework.common.security.details.CustomUserDetails;
import com.framework.common.security.dto.LoginJwtTokenDto;
import com.framework.common.security.dto.JWTInfo;
import com.framework.common.security.dto.UserInfo;
import com.framework.common.security.service.UserInfoService;
import com.framework.common.security.util.JwtUtil;
import com.framework.common.factory.ErrorMessageSourceFactory;
import com.framework.common.handler.CommonApiResponse;
import com.framework.common.util.BeanUtil;
import com.framework.common.util.HttpUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String secretKey;
    private final String accessTokenexpirationTime;
    private final String refreshTokenexpirationTime;
    private final ObjectMapper objectMapper;
    private final UserInfoService userInfoService;

    /**
     * login 요청을 하면 로그인 시도를 위해서 실행되는 함수
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Login process start !");
            log.debug("*********************************");
        }

        try {
            ObjectMapper om = new ObjectMapper();
            UserInfo user = om.readValue(request.getInputStream(), UserInfo.class);

            if(StringUtils.isEmpty(user.getUserEmail()) || StringUtils.isEmpty(user.getUserEmail())) {
                request.setAttribute("AUTHENTICATION_FAIL", "ERRCOMLO000001");
                request.getRequestDispatcher("/authentication-fail").forward(request, response);
            }

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword());

            Authentication authentication = authenticationManager.authenticate(token);
            return authentication;

        } catch (AuthenticationException e) {
            log.error("- CustomUsernamePasswordAuthenticationFilter AuthenticationException : {}", e.getMessage());
            //throw new RuntimeException(e);
            throw e;
        } catch (Exception e) {
            log.error("- CustomUsernamePasswordAuthenticationFilter RuntimeException : {}", e);
            throw new BadCredentialsException("");
        } finally {
            if(log.isDebugEnabled()) {
                log.debug("*********************************");
                log.debug("* Login process end !");
                log.debug("*********************************");
            }
        }
    }

    /**
     * 로그인 성공시 처리
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Login success start !");
            log.debug("*********************************");
        }

        CustomUserDetails principalDetails = (CustomUserDetails)authResult.getPrincipal();
        //access Token 생성
        String accessToken = JwtUtil.createJWT(String.valueOf(principalDetails.getUserInfo().getUserId()), secretKey, accessTokenexpirationTime);
        //refresh Token 생성
        String refreshToken = JwtUtil.createJWT(String.valueOf(principalDetails.getUserInfo().getUserId()), secretKey, refreshTokenexpirationTime);

        if(log.isDebugEnabled()) {
            log.debug("- USER ID : {}", principalDetails.getUsername());
            log.debug("- accessToken : {}", accessToken);
            log.debug("- refreshToken : {}", refreshToken);
        }

        // 발급된 사용자 토큰 정보 저장
        JWTInfo jWTInfo = userInfoService.getUserToken(principalDetails.getUsername());
        if(jWTInfo == null || "".equals(jWTInfo.getUserId())) {
            //사용자 토큰 정보가 없을 경우 저장
            JWTInfo saveJWT = new JWTInfo();
            saveJWT.setUserId(Integer.parseInt(principalDetails.getUsername()));
            saveJWT.setUserAccessToken(accessToken);
            saveJWT.setUserRefreshToken(refreshToken); //TODO refreshToken을 양방향 암호화 거칠지 고민필요
            userInfoService.saveUserToken(saveJWT);
        } else {
            //사용자 토큰 정보가 있을 경우 수정
            jWTInfo.setUserId(Integer.parseInt(principalDetails.getUsername()));
            jWTInfo.setUserAccessToken(accessToken);
            jWTInfo.setUserRefreshToken(refreshToken); //TODO refreshToken을 양방향 암호화 거칠지 고민필요
            userInfoService.updateUserToken(jWTInfo);
        }

        //client에 access, refresh Token 전달
        LoginJwtTokenDto tokenDto = new LoginJwtTokenDto();
        tokenDto.setAccessToken(accessToken);
        tokenDto.setRefreshToken(refreshToken);
        CommonApiResponse res = CommonApiResponse.ok(tokenDto);

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(res));

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Login success end !");
            log.debug("*********************************");
        }
    }

    /**
     * AuthenticationException 처리
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Login fail start !");
            log.debug("*********************************");
        }

        //api 호출이 아닐경우 500페이지로 던짐
        if(!HttpUtil.isAjax(request)) {
            request.getRequestDispatcher("/error/500").forward(request, response);
        }

        String errorCode = "";

        if(failed instanceof BadCredentialsException) {
            //사용자 정보 없음
            if(log.isDebugEnabled()) {
                log.debug("- Login result [No user information] :: id = {}", failed.getMessage());
            }
            errorCode = "ERRCOMLO000001";
        } else if(failed instanceof InternalAuthenticationServiceException) {
            //패스워드 불일치
            if(log.isDebugEnabled()) {
                log.debug("- Login result [Password mismatch] :: id = {}", failed.getMessage());
            }
            errorCode = "ERRCOMLO000001";
        } else {
            //그 밖의 에러
            if(log.isDebugEnabled()) {
                log.debug("- Login result [{}] :: id = {}", failed.getClass().getSimpleName(), failed.getMessage());
            }
            errorCode = "ERRCOMLO000002";
        }

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);
        String errorMessage = errorMessageSourceFactory.getMessage(errorCode);
        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, errorMessage);

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(res));

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Login fail end !");
            log.debug("*********************************");
        }
    }
}
