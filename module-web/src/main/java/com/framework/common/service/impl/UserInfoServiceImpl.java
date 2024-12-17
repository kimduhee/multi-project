package com.framework.common.service.impl;

import com.framework.common.mapper.UserInfoMapper;
import com.framework.common.security.dto.UserInfo;
import com.framework.common.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoByEmail(String userEmail) {
        return userInfoMapper.getUserInfoByEmail(userEmail);
    }

    @Override
    public UserInfo getUserInfoById(String userId) {
        return userInfoMapper.getUserInfoById(userId);
    }


}
