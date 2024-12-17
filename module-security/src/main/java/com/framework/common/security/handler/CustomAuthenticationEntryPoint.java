package com.framework.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.framework.common.factory.ErrorMessageSourceFactory;
import com.framework.common.handler.CommonApiResponse;
import com.framework.common.util.BeanUtil;
import com.framework.common.util.HttpUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if(log.isInfoEnabled()) {
            log.info("*******************************************************");
            log.info("* CustomAuthenticationEntryPoint exception msg :: {}", authException.getMessage());
            log.info("* request :: {}", request.getRequestURI());
            log.info("*******************************************************");
        }

        if(!HttpUtil.isAjax(request)) {
            response.sendRedirect("/error/401");
            return;
        }

        String errorCode = (String)request.getAttribute("AUTHENTICATION_FAIL");
        String errorMessage = "";

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        if(StringUtils.isEmpty(errorCode)) {
            errorCode = "ERRCOMLO000401";
        }

        errorMessage = errorMessageSourceFactory.getMessage(errorCode);
        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.UNAUTHORIZED, errorCode, errorMessage);

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //response.getWriter().write(new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR));
        response.getWriter().write(objectMapper.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writeValueAsString(res));
    }
}
