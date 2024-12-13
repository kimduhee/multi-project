package com.framework.web.common.security.mapper;

import com.framework.web.common.security.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    public UserInfo getUserInfoByEmail(String userEmail);
    public UserInfo getUserInfoById(String userEmail);
}
