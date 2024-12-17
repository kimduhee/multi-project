package com.framework.common.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JWTInfo {
    private String userAccessToken;
    private String userRefreshToken;
    private int userId;
}
