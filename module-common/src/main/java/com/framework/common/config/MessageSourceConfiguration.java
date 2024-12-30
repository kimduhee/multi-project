package com.framework.common.config;

import com.framework.common.factory.GlobalEnvironmentFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * packageName    : com.framework.common.config
 * fileName       : MessageSourceConfiguration
 * author         : NAMANOK
 * date           : 2024-12-30
 * description    :
 *  메세지 처리 설정
 * -공통에러, security에러, 서비스 에러 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        NAMANOK       최초 생성
 */
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
