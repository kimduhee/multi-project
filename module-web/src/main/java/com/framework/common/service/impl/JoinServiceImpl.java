package com.framework.common.service.impl;

import com.framework.common.mapper.JoinMapper;
import com.framework.common.security.service.JoinService;
import com.framework.common.security.service.dto.JoinSInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class JoinServiceImpl implements JoinService {

    private final JoinMapper userJoinMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int join(JoinSInDto sInDto) {
        sInDto.setUserRole("ROLE_USER");
        sInDto.setUserPassword(bCryptPasswordEncoder.encode(sInDto.getUserPassword()));
        return userJoinMapper.join(sInDto);
    }

}
