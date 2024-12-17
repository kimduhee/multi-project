package com.framework.common.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserInfo {
    private int userId;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userPassword;
    private String userRole;
    private String regId;
    private Date regDt;
    private String updId;
    private Date updDt;
    public List<String> getRoleList() {
        if(userRole != null) {
            if(this.userRole.length() > 0) {
                return Arrays.asList(this.userRole.split(","));
            }
        }
        return new ArrayList<>();
    }
}
