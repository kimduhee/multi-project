package com.framework.common.config;

import com.framework.common.factory.GlobalEnvironmentFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class MessageSourceConfiguration {

    //@Autowired
    private final GlobalEnvironmentFactory globalEnvironmentFactory;

    @Bean(name="errorMessageSource")
    public MessageSource errorMessageSource() {

        String[] errorArr = {
                "classpath:/message/error/error_common_"+globalEnvironmentFactory.getActiveProfile(),
                "classpath:/message/error/error_common",

                "classpath:/message/error/error_security_"+globalEnvironmentFactory.getActiveProfile(),
                "classpath:/message/error/error_security",

                "classpath:/message/error/error_"+globalEnvironmentFactory.getActiveProfile(),
                "classpath:/message/error/error"
        };

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //messageSource.setBasenames("classpath:/message/error/error_"+globalEnvironmentFactory.getActiveProfile(), "classpath:/message/error/error");
        messageSource.setBasenames(errorArr);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

    @Bean(name="errorMessageSourceAccessor")
    public MessageSourceAccessor errorMessageSourceAccessor() {
        return new MessageSourceAccessor(errorMessageSource(), Locale.KOREA);
    }
}
