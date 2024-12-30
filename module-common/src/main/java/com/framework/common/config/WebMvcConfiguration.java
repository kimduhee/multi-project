package com.framework.common.config;

import com.framework.common.filter.XssFilter;
import com.framework.common.interceptor.GlobalInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.concurrent.Executor;

/**
 * packageName    : com.framework.common.config
 * fileName       : WebMvcConfiguration
 * author         : NAMANOK
 * date           : 2024-12-19
 * description    : 
 * 웹 MVC 사용자 설정 class
 * -resource 설정
 * -interceptor 설정
 * -Xss filter 적용
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-19        NAMANOK       최초 생성
 */
@Slf4j
@Configuration
//@EnableAsync //TODO 비동기 필요시 주석해제
public class WebMvcConfiguration implements WebMvcConfigurer {

    //업로드 이미지 저장경로
    @Value("${spring.servlet.multipart.location}")
    private String uploadImagePath;

    /**
     * resource handler config
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///"+uploadImagePath)    // 웹에서 이미지 호출시 'file:///' 설정됨
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    /**
     * interceptor registration
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalInterceptor())
                .excludePathPatterns("/images/**", "/css/**", "/js/**");
    }

    /**
     * XSS filter registration
     * @return
     */
    @Bean
    public FilterRegistrationBean<XssFilter> cstomXssFilter() {
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter());
        //registrationBean.addUrlPatterns("");
        return registrationBean;
    }
/*
    TODO 비동기 필요시 아래 주석 해제
*/
/* 
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("async-servlet-");
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.initialize();
        return executor;
    }
*/    
}
