package com.framework.common.security.config;

import com.framework.common.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.framework.common.security.filter.JwtAuthenticationFilter;
import com.framework.common.security.handler.CustomAccessDeniedHandler;
import com.framework.common.security.handler.CustomAuthenticationEntryPoint;
import com.framework.common.security.provider.CustomAuthenticationProvider;
import com.framework.common.security.service.UserInfoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

/**
 * Security 설정
 * Spring security 5.7 이상에서는 더 이상 WebSecurityConfigurerAdapter 사용을 권장하지 않음
 * 따라서 SecurityFilterChain Bean 등록으로 처리
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Slf4j
@PropertySource("classpath:/application-security-${spring.profiles.active}.yml")
public class SecurityConfiguration {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration.access-token.time}")
    private String expirationTime;

    @Value("${jwt.excluding-check.equals-url}")
    private String equalsUrl;

    @Value("${jwt.excluding-check.start-with-url}")
    private String startWithUrl;

    @Value("${auth.excluding-check.pattern}")
    private String authPattern;

    private final UserInfoService userInfoService;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain config(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception{

        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

        String[] authPatternArr = authPattern.split(",");

        if(log.isInfoEnabled()) {
            log.info("************************************");
            log.info("* Allow path List");
        }
        //인가처리에서 제외 될 패턴
        MvcRequestMatcher[] permitAllWhiteList = new MvcRequestMatcher[authPatternArr.length];
        int index = 0;
        for(String arr : authPatternArr) {
            if(log.isInfoEnabled()) {
                log.info("* - {}", arr);
            }
            permitAllWhiteList[index] = mvc.pattern(arr);
            index++;
        }
        if(log.isInfoEnabled()) {
            log.info("************************************");
        }

        http
                //CSRF 정책 설정
                .csrf(csrf ->csrf.disable())

                //CORS 정책 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                //JWT 토큰 방식 사용으로 시큐리티가 세션을 생성하지 않고 존재해도 사용하지 않음
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //미인증(401)에 대한 에러 처리
                .exceptionHandling(conf -> conf.authenticationEntryPoint(authenticationEntryPoint()))
                //미인가(403)에 대한 에러 처리
                .exceptionHandling(conf -> conf.accessDeniedHandler(accessDeniedHandler()));

        http
                //JWT 토큰 방식 사용으로 인한 폼 로그인 미사용
                .formLogin(conf -> conf.disable());

        http
                //JWT 토큰 방식 사용으로 browser 사용자 인증 미사용
                .httpBasic(conf -> conf.disable());

        http
                //h2-console 사용을 위한 설정
                .headers(conf -> conf.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))

                //인가 설정
                .authorizeHttpRequests(conf -> conf.requestMatchers(permitAllWhiteList).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll() //h2-console 사용을 위한 설정
                        .anyRequest().authenticated());

        http
                //폼로그인 미사용으로 UsernamePasswordAuthenticationFilter 구현
                .addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                
                //인가를 위한 JWT토큰 검증 필터 구현
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * '/login' 으로 들어온 요청에 대해서는 CustomUsernamePasswordAuthenticationFilter이 인증처리를 진행한다.
     * @return
     * @throws Exception
     */
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter authorizationFilter = new CustomUsernamePasswordAuthenticationFilter(authenticationManager(), secretKey, expirationTime);
        authorizationFilter.setFilterProcessesUrl("/login");
        return authorizationFilter;
    }

    /**
     * 인가를 위한 JWT 토큰 검증 필터
     * @return
     */
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        //return new JwtAuthenticationFilter(userInfoService);
        return new JwtAuthenticationFilter(secretKey, userInfoService) {
            @Override
            protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
                boolean result = false;
                String path = request.getServletPath();
                
                //JWT 토큰 검증 및 SecurityContext 생성제한 URL(yml에서 관리)
                //URL 일치
                if(!StringUtils.isEmpty(equalsUrl)) {
                    String[] equalsUrlArr = equalsUrl.split(",");
                    for(String arr : equalsUrlArr) {
                        if(StringUtils.defaultString(arr).equals(path)) {
                            if(log.isInfoEnabled()) {
                                log.info("* Token check pass path :: {}", path);
                            }
                            return true;
                        }
                    }
                }
                //URL 앞부분 시작
                if(!StringUtils.isEmpty(startWithUrl)) {
                    String[] startWithUrlArr = startWithUrl.split(",");
                    for(String arr : startWithUrlArr) {
                        if(path.startsWith(arr)) {
                            if(log.isInfoEnabled()) {
                                log.info("* Token check pass path :: {}", path);
                            }
                            return true;
                        }
                    }
                }

                return false;
            }
        };
    }

    /**
     * 인증되지 않은 사용자 접근(401)에 따른 에러 처리
     * @return
     */
    @Bean
    public CustomAuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    /**
     * 인가되지 않은 사용자 접근(403)에 따른 에러 처리
     * @return
     */
    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /**
     * CORS 정책 설정
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));   //모든 도메인 허용
        configuration.setAllowedHeaders(List.of("*"));          //모든 헤더 허용
        configuration.setAllowedMethods(List.of("GET", "POST"));    //GET, POST 메소드 허용
        configuration.setAllowCredentials(true);                    //자격 증명 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //모든 경로에 대해 적용
        return source;
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(bCryptPasswordEncoder(), userDetailsService);
    }

    /**
     * 패스워드 암호화 사용
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ProviderManager에 CustomAuthenticationProvider로 지정
     * 여러 Provider를 넘겨 줄수 있음
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        CustomAuthenticationProvider authProvider = customAuthenticationProvider();
        return new ProviderManager(authProvider);
    }
}
