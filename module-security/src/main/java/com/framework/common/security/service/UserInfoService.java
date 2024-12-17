package com.framework.common.security.service;

import com.framework.common.security.dto.UserInfo;
import org.springframework.stereotype.Service;

public interface UserInfoService {

    public UserInfo getUserInfoByEmail(String userEmail);

    public UserInfo getUserInfoById(String userId);
}
