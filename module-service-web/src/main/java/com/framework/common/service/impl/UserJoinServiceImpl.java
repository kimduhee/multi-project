package com.framework.common.service.impl;

import com.framework.common.mapper.UserJoinMapper;
import com.framework.common.service.UserJoinService;
import com.framework.common.service.dto.UserJoinSInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserJoinServiceImpl implements UserJoinService {

    private final UserJoinMapper userJoinMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int join(UserJoinSInDto sInDto) {
        sInDto.setUserRole("ROLE_USER");
        sInDto.setUserPassword(bCryptPasswordEncoder.encode(sInDto.getUserPassword()));
        return userJoinMapper.join(sInDto);
    }

}
