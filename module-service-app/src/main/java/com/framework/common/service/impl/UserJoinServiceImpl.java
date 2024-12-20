package com.framework.common.service.impl;

import com.framework.common.entity.UserInfoJpa;
import com.framework.common.exception.BizException;
import com.framework.common.repository.UserInfoRepository;
import com.framework.common.service.UserJoinService;
import com.framework.common.service.dto.UserJoinSInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserJoinServiceImpl implements UserJoinService {

    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void join(UserJoinSInDto sInDto) {

        UserInfoJpa userInfoJpa = userInfoRepository.findByUserEmail(sInDto.getUserEmail());
        if(userInfoJpa != null) {
            throw new BizException("ERRBIZJO000000");
        }

        userInfoJpa = UserInfoJpa.builder()
                .userName(sInDto.getUserName())
                .userEmail(sInDto.getUserEmail())
                .userNickname(sInDto.getUserNickname())
                .userPassword(bCryptPasswordEncoder.encode(sInDto.getUserPassword()))
                .userRole("ROLE_USER").build();

        userInfoRepository.save(userInfoJpa);
    }

}
