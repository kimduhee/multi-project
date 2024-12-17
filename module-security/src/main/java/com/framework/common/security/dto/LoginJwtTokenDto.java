package com.framework.common.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginJwtTokenDto {
    private String accessToken;
    private String refreshToken;
}
