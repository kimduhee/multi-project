package com.framework.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;

public class HttpUtil {

    /**
     * request 건이 화면인지 ajax인지 구별
     *
     * @param httpServletRequest
     * @return
     */
    public static boolean isAjax(HttpServletRequest httpServletRequest) {

        boolean isViewResponse = false;

        //Request Content-type 에 application/json 이 포함될 경우 ajax(API) 통신
        if(httpServletRequest.getContentType() != null && httpServletRequest.getContentType().indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) {
            return true;
        }

        //Request Headers 의 Accept에 application/json 이 포함될 경우 ajax(API) 통신
        if(httpServletRequest.getHeader("accept") != null && httpServletRequest.getHeader("accept").indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) {
            return true;
        }

        return isViewResponse;
    }
}
