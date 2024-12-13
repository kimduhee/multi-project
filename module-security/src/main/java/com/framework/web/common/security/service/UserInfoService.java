package com.framework.web.common.security.service;

import com.framework.web.common.security.dto.UserInfo;
import com.framework.web.common.security.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    public UserInfo getUserInfoByEmail(String userEmail) {
        return userInfoMapper.getUserInfoByEmail(userEmail);
    }

    public UserInfo getUserInfoById(String userId) {
        return userInfoMapper.getUserInfoById(userId);
    }
}
