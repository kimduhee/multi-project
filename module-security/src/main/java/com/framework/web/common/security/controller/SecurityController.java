package com.framework.web.common.security.controller;

import com.framework.web.common.factory.ErrorMessageSourceFactory;
import com.framework.web.common.handler.CommonApiResponse;
import com.framework.web.common.util.BeanUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecurityController {

    /**
     * 로그인 처리에 대한 에러
     * @param request
     * @return
     */
    @PostMapping(value="/authentication-fail")
    public ResponseEntity<CommonApiResponse> authenticationFail(HttpServletRequest request) {

        String errorCode = (String)request.getAttribute("AUTHENTICATION_FAIL");
        String errorMessage = "";
        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        if(StringUtils.isEmpty(errorCode)) {
            errorCode = "ERRCOMLO000000";
        }

        errorMessage = errorMessageSourceFactory.getMessage(errorCode);

        if(log.isInfoEnabled()) {
            log.info("authenticationFail : [{}]{}", errorCode, errorMessage);
        }

        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR,errorCode, errorMessage);

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
