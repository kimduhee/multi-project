package com.framework.web.common.security.provider;

import com.framework.web.common.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 사용자 인증 담당
 */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

    /**
     * 사용자 정의 인증 수행
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Provider start !");
            log.debug("*********************************");
        }

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(log.isDebugEnabled()) {
            log.debug("- username : {}", username);
            //log.debug("- password : {}", password);
        }

        CustomUserDetails principalDetails = (CustomUserDetails)userDetailsService.loadUserByUsername(username);

        if(principalDetails == null || principalDetails.getUserInfo() == null) {
            throw new BadCredentialsException(username);
        }

        if(!bCryptPasswordEncoder.matches(password, principalDetails.getUserInfo().getUserPassword())) {
            throw new InternalAuthenticationServiceException(username);
        }

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* Provider end !");
            log.debug("*********************************");
        }

        return new UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                principalDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //전달 받은 authentication가 UsernamePasswordAuthenticationToken 하위 클래스 또는 UsernamePasswordAuthenticationToken클래스인지 확인
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
