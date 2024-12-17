package com.framework.common.security.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class RefreshTokenCInDto {

    //refresh token
    @Schema(description = "refresh token", example = "")
    @NotEmpty(message="토큰값이 존재하지 않습니다.")
    private String userRefreshToken;
}
