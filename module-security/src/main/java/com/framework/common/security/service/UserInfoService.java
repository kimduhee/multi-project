package com.framework.common.security.service;

import com.framework.common.security.dto.JWTInfo;
import com.framework.common.security.dto.UserInfo;

public interface UserInfoService {

    public UserInfo getUserInfoByEmail(String userEmail);
    public UserInfo getUserInfoById(String userId);
    public JWTInfo getUserToken(String userId);
    public void updateUserToken(JWTInfo saveJWT);
    public void saveUserToken(JWTInfo saveJWT);
}
