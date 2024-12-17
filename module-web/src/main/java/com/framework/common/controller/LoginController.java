package com.framework.common.controller;

import com.framework.common.controller.dto.LoginCInDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인 API", description = "로그인 API")
@RestController
public class LoginController {

    @Operation(summary="로그인", description = "더미 로그인")
    @PostMapping(value="/login")
    public String dummyLogin(@RequestBody LoginCInDto cInDto) {
        return "";
    }
}
