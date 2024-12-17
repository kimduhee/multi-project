package com.framework.common.mapper;

import com.framework.common.dto.UserJoin;
import com.framework.common.service.dto.JoinSInDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    public int join(JoinSInDto sInDto);
}
