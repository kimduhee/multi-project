package com.framework.common.mapper;

import com.framework.common.security.dto.JWTInfo;
import com.framework.common.security.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    public UserInfo getUserInfoByEmail(String userEmail);
    public UserInfo getUserInfoById(String userId);
    public JWTInfo getUserToken(String userId);
    public int updateUserToken(JWTInfo saveJWT);
    public int saveUserToken(JWTInfo saveJWT);
}
