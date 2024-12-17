package com.framework.common.security.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinSInDto {
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userPassword;
    private String userRole;
}
