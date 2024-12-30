package com.framework.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.handler.CommonApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * packageName    : com.framework.common.aop
 * fileName       : LoggingAop
 * author         : NAMANOK
 * date           : 2024-12-30
 * description    :
 *  AOP 설정
 * -local 및 dev 환경에서만 적용됨
 * -request 및 response 값 로그적용
 * -최종 서비스 처리 시간 측정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        NAMANOK       최초 생성
 */
@Slf4j
@Aspect
@Component
@Profile(value={"local","dev"})
@RequiredArgsConstructor
public class LoggingAop {

    @Pointcut("execution(* com.framework..*Controller.*(..))")
    private void controllerCut(){};

    private final ObjectMapper objectMapper;

    @Around("controllerCut()")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 타겟 호출
        } finally {
            stopWatch.stop();
            if (log.isInfoEnabled()) {
                log.info("******************************************************");
                log.info("* Service execution information");
                log.info("* - method : {}", joinPoint.getSignature().toShortString());
                log.info("* - Total run time : {}", stopWatch.getTotalTimeSeconds());
                log.info("******************************************************");
            }
        }
    }

    @Before("controllerCut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        try {
            if(log.isInfoEnabled()) {
                log.info("* Controller Start!");
                log.info("* - path : {}", request.getRequestURI());
                log.info("* - Method name : {}", getMethod(joinPoint));
            }

            Object[] args = joinPoint.getArgs();

            if(args.length <= 0) {
                if(log.isInfoEnabled()) {
                    log.info("* - No parameter");
                }
            } else {
                for(Object arg : args) {
                    if(log.isInfoEnabled()) {
                        log.info("* - Parameter type : {}", arg.getClass().getSimpleName());
                        try {
                            log.info("* - Parameter value : {}", objectMapper.writeValueAsString(arg));
                        } catch(Exception e) {
                            log.info("* - Parameter value : Not dto type");
                        }
                    }
                }
            }
        } catch(Exception e) {
            log.info("*");
        }
    }

    @AfterReturning(value="controllerCut()", returning="returnObj")
    private void afterReturnLog(JoinPoint joinPoint, Object returnObj) {

        try {
            if (log.isInfoEnabled()) {
                //Method method = getMethod(joinPoint);
                log.info("* Controller End!");
                log.info("* - Method name : {}", getMethod(joinPoint));
                if(returnObj instanceof String) {
                    log.info("* - Return value : {}.html", returnObj);
                } else if(returnObj instanceof ResponseEntity) {
                    Object obj = ((CommonApiResponse) ((ResponseEntity) returnObj).getBody()).getData();
                    log.info("* - Return value : {}", objectMapper.writeValueAsString(obj));
                }
            }
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    private String getMethod(JoinPoint joinpoint) {
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        return joinpoint.getSignature().getDeclaringTypeName()+"."+signature.getMethod().getName();
    }
}
