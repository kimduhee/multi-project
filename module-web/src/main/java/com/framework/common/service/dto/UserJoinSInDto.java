package com.framework.common.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinSInDto {
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userPassword;
    private String userRole;
}
