package com.framework.web.common.security.details;

import com.framework.web.common.security.dto.UserInfo;
import com.framework.web.common.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(log.isDebugEnabled()) {
            log.debug("*********************************");
            log.debug("* User info inquiry Start!");
            log.debug("*********************************");
        }

        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException(username);
        }

        UserInfo user = userInfoService.getUserInfoByEmail(username);
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserInfo(user);

        if(log.isDebugEnabled()) {
            if(user != null) {
                log.debug("- user : {}", user.getUserEmail());
            } else {
                log.debug("- user : does not exist");
            }
            log.debug("*********************************");
            log.debug("* User info inquiry End!");
            log.debug("*********************************");
        }

        return customUserDetails;
    }
}
