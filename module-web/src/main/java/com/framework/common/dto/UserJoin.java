package com.framework.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoin {
    private long userId;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userPassword;
    private String userRole;
}
