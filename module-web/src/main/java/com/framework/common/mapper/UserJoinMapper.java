package com.framework.common.mapper;

import com.framework.common.service.dto.UserJoinSInDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserJoinMapper {

    public int join(UserJoinSInDto sInDto);
}
