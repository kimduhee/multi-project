package com.framework.common.service.impl;

import com.framework.common.mapper.UserInfoMapper;
import com.framework.common.security.dto.JWTInfo;
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

    @Override
    public JWTInfo getUserToken(String userId) {
        return userInfoMapper.getUserToken(userId);
    }

    @Override
    public void updateUserToken(JWTInfo saveJWT) {
        userInfoMapper.updateUserToken(saveJWT);
    }

    @Override
    public void saveUserToken(JWTInfo saveJWT) {
        userInfoMapper.saveUserToken(saveJWT);
    }
}
