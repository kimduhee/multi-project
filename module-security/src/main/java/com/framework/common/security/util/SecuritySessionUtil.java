package com.framework.common.security.util;

import com.framework.common.security.details.CustomUserDetails;
import com.framework.common.security.dto.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecuritySessionUtil {

    /**
     * JWT에서 추출된 userId로 SecurityContext에 저장된 사용자 정보 조회
     * @return
     */
    public static UserInfo getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = ((CustomUserDetails)principal).getUserInfo();
        return userInfo;
    }
}
