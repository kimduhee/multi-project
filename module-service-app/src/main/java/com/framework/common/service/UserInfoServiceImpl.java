package com.framework.common.service;

import com.framework.common.entity.UserInfoJpa;
import com.framework.common.repository.UserInfoRepository;
import com.framework.common.security.dto.JWTInfo;
import com.framework.common.security.dto.UserInfo;
import com.framework.common.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfo getUserInfoByEmail(String userEmail) {

        UserInfo userInfo = new UserInfo();
        UserInfoJpa UserInfoJpa = userInfoRepository.findByEmail(userEmail);
        BeanUtils.copyProperties(UserInfoJpa, userInfo);
        return userInfo;
    }

    public UserInfo getUserInfoById(String userId) {
        UserInfo userInfo = new UserInfo();
        UserInfoJpa UserInfoJpa = userInfoRepository.findById(Long.parseLong(userId));
        BeanUtils.copyProperties(UserInfoJpa, userInfo);
        return userInfo;
    }

    @Override
    public JWTInfo getUserToken(String userId) {
        //TODO 구현 해야됨
        return null;
    }

    @Override
    public void saveUserToken(JWTInfo saveJWT) {
        //TODO 구현 해야됨
    }

    @Override
    public void updateUserToken(JWTInfo saveJWT) {
        //TODO 구현 해야됨
    }
}
