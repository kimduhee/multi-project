package com.framework.common.service.impl;

import com.framework.common.entity.UserInfoJpa;
import com.framework.common.entity.UserInfoTokenJpa;
import com.framework.common.exception.BizException;
import com.framework.common.repository.UserInfoRepository;
import com.framework.common.repository.UserInfoTokenRepository;
import com.framework.common.security.dto.JWTInfo;
import com.framework.common.security.dto.UserInfo;
import com.framework.common.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoTokenRepository userInfoTokenRepository;

    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public UserInfo getUserInfoByEmail(String userEmail) {
        UserInfo userInfo = new UserInfo();
        UserInfoJpa userInfoJpa = userInfoRepository.findByUserEmail(userEmail);
        BeanUtils.copyProperties(userInfoJpa, userInfo);
        return userInfo;
    }

    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public UserInfo getUserInfoById(String userId) {
        UserInfo userInfo = new UserInfo();
        UserInfoJpa UserInfoJpa = userInfoRepository.findByUserId(Integer.parseInt(userId));
        BeanUtils.copyProperties(UserInfoJpa, userInfo);
        return userInfo;
    }

    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public JWTInfo getUserToken(String userId) {
        JWTInfo jWTInfo = null;
        UserInfoTokenJpa userInfoTokenJpa = userInfoTokenRepository.findByUserId(Integer.parseInt(userId));
        if(userInfoTokenJpa != null) {
            jWTInfo = new JWTInfo();
            jWTInfo.setUserAccessToken(userInfoTokenJpa.getUserAccessToken());
            jWTInfo.setUserRefreshToken(userInfoTokenJpa.getUserRefreshToken());
            jWTInfo.setUserId(userInfoTokenJpa.getUserId());
        }
        return jWTInfo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void saveUserToken(JWTInfo saveJWT) {
        UserInfoTokenJpa userInfoTokenJpa = UserInfoTokenJpa.builder()
                        .userId(saveJWT.getUserId())
                        .userAccessToken(saveJWT.getUserAccessToken())
                        .userRefreshToken(saveJWT.getUserRefreshToken())
                        .regId(saveJWT.getUserId())
                        .updId(saveJWT.getUserId()).build();
        userInfoTokenRepository.save(userInfoTokenJpa);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void updateUserToken(JWTInfo saveJWT) {

        UserInfoTokenJpa userInfoTokenJpa = userInfoTokenRepository.findByUserId(saveJWT.getUserId());

        userInfoTokenJpa = UserInfoTokenJpa.builder()
                .userId(saveJWT.getUserId())
                .userAccessToken(saveJWT.getUserAccessToken())
                .userRefreshToken(saveJWT.getUserRefreshToken())
                //.regId(saveJWT.getUserId())
                .updId(saveJWT.getUserId()).build();
        userInfoTokenRepository.save(userInfoTokenJpa);
    }
}
