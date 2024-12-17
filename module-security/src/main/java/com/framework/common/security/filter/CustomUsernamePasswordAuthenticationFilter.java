package com.framework.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.framework.common.security.details.CustomUserDetails;
import com.framework.common.security.dto.LoginJwtTokenDto;
import com.framework.common.security.dto.UserInfo;
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
import org.springframework.beans.factory.annotation.Value;
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
    private final String expirationTime;
    private final ObjectMapper objectMapper;

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
        //JWT Token 생성
        String jwtToken = JwtUtil.createJWT(String.valueOf(principalDetails.getUserInfo().getUserId()), secretKey, expirationTime);

        if(log.isDebugEnabled()) {
            log.debug("- USER ID : {}", principalDetails.getUsername());
            log.debug("- JWT token : {}", jwtToken);
        }

        LoginJwtTokenDto tokenDto = new LoginJwtTokenDto();
        tokenDto.setAccessToken(jwtToken);
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
