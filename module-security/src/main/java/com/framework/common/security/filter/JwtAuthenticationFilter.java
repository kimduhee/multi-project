package com.framework.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.framework.common.factory.ErrorMessageSourceFactory;
import com.framework.common.handler.CommonApiResponse;
import com.framework.common.security.details.CustomUserDetails;
import com.framework.common.security.dto.UserInfo;
import com.framework.common.security.service.UserInfoService;
import com.framework.common.security.util.JwtUtil;
import com.framework.common.util.BeanUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String secretKey;
    private final UserInfoService userInfoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* JWT token validation start !");
            log.debug("*********************************");
        }

        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")) {

            if(log.isDebugEnabled()) {
                log.debug("- JWT token validation execute ");
            }

            token = token.substring(7);
            String validationResult = JwtUtil.validateToken(token, secretKey);

            if(!"00".equals(validationResult)) {
                responseValidationJWT(request, response);
                return;
            }

            String userId = JwtUtil.getUserId(token, secretKey);

            if(log.isDebugEnabled()) {
                log.debug("- token userId :: {}", userId);
            }

            //JWT 토큰에 의해 userId 추출 가능시에만 SecurityContext 생성
            if(!StringUtils.isEmpty(userId)) {
                UserInfo userInfo = (UserInfo)userInfoService.getUserInfoById(userId);
                CustomUserDetails principalDetails = new CustomUserDetails(userInfo);
                if(userId != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                if(log.isDebugEnabled()) {
                    log.debug("- JWT userId empty ");
                }
            }
        } else {
            if(log.isDebugEnabled()) {
                log.debug("- No JWT in Header");
            }
        }

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* JWT token validation end !");
            log.debug("*********************************");
        }

        filterChain.doFilter(request, response);
    }

    public void responseValidationJWT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        String errorCode = "ERRCOMLO000003";
        String errorMessage = errorMessageSourceFactory.getMessage(errorCode);
        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.UNAUTHORIZED, errorCode, errorMessage);

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(res));
    }
}
