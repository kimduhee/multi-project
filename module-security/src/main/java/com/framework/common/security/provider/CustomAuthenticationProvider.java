package com.framework.common.security.provider;

import com.framework.common.security.details.CustomUserDetails;
import com.framework.common.security.service.UserInfoService;
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
            log.debug("* Provider start !");
        }

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(log.isDebugEnabled()) {
            log.debug("- username : {}", username);
            //log.debug("- password : {}", password);
        }

        CustomUserDetails principalDetails = (CustomUserDetails)userDetailsService.loadUserByUsername(username);

        //사용자 정보 없을 경우
        if(principalDetails == null || principalDetails.getUserInfo() == null) {
            if(log.isDebugEnabled()) {
                log.debug("- Not user information");
            }
            throw new BadCredentialsException(username);
        }

        //비밀번호 불일치 시
        if(!bCryptPasswordEncoder.matches(password, principalDetails.getUserInfo().getUserPassword())) {
            if(log.isDebugEnabled()) {
                log.debug("- Password mismatch");
            }
            throw new InternalAuthenticationServiceException(username);
        }

        if(log.isDebugEnabled()) {
            log.debug("* Provider end !");
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
