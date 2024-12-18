package com.framework.common.handler;

import com.framework.common.exception.BizException;
import com.framework.common.factory.ErrorMessageSourceFactory;
import com.framework.common.util.BeanUtil;
import com.framework.common.util.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @Autowired
    private ContentNegotiatingViewResolver contentNegotiatingViewResolver;

    @Autowired
    ErrorMessageSourceFactory errorMessageSourceFactory;

    /**
     * 404 not found 처리
     * @param e
     * @param httpServletRequest
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNotFoundException(NoHandlerFoundException e, HttpServletRequest httpServletRequest) {

        if(!HttpUtil.isAjax(httpServletRequest)) {
            ModelAndView mav = new ModelAndView("forward:/error/404");
            return mav;
        }

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.NOT_FOUND,"ERRCOMCM000404", errorMessageSourceFactory.getMessage("ERRCOMCM000404"));

        if(log.isDebugEnabled()) {
            log.debug("NoHandlerFoundException : [ERRCOMCM000404]{}", errorMessageSourceFactory.getMessage("ERRCOMCM000404"));
            log.debug("error =>", e);
        }

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    /**
     * Controller input validation check
     * @param e
     * @param httpServletRequest
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {

        if(!HttpUtil.isAjax(httpServletRequest)) {
            ModelAndView mav = new ModelAndView("forward:/error/400");
            return mav;
        }

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.BAD_REQUEST, "ERRCOMCM000400", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        if(log.isDebugEnabled()) {
            log.debug("BizException : [ERRCOMCM000400]{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            log.debug("error =>", e);
        }

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    /**
     * 커스텀 BizException 처리
     * @param e
     * @param httpServletRequest
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Object handleBizException(BizException e, HttpServletRequest httpServletRequest) {

        if(!HttpUtil.isAjax(httpServletRequest)) {
            ModelAndView mav = new ModelAndView("forward:/error/500");
            return mav;
        }

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        if(StringUtils.isEmpty(e.getMessage())) {
            e.setErrMessage(errorMessageSourceFactory.getMessage(e.getErrCode()));
        }

        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getErrCode(), e.getMessage());

        if(log.isDebugEnabled()) {
            log.debug("BizException : [{}]{}",e.getErrCode(), e.getMessage());
            log.debug("error =>", e);
        }

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * RuntimeException 처리
     * @param e
     * @param httpServletRequest
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeExceptiontion(RuntimeException e, HttpServletRequest httpServletRequest) {

        if(!HttpUtil.isAjax(httpServletRequest)) {
            ModelAndView mav = new ModelAndView("forward:/error/500");
            return mav;
        }

        ErrorMessageSourceFactory errorMessageSourceFactory = BeanUtil.getBean(ErrorMessageSourceFactory.class);

        CommonApiResponse res = CommonApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR,"ERRCOMCM000000", errorMessageSourceFactory.getMessage("ERRCOMCM000000"));

        if(log.isDebugEnabled()) {
            log.debug("RuntimeException : [ERRCOMCM000000]{}", errorMessageSourceFactory.getMessage("ERRCOMCM000000"));
            log.debug("error =>", e);
        }

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
