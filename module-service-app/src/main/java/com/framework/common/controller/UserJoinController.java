package com.framework.common.controller;

import com.framework.common.controller.dto.UserJoinCInDto;
import com.framework.common.controller.dto.UserJoinCOutDto;
import com.framework.common.handler.CommonApiResponse;

import com.framework.common.service.UserJoinService;
import com.framework.common.service.dto.UserJoinSInDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원가입 API", description = "회원가입 API")
@RequiredArgsConstructor
@Slf4j
@RestController
public class UserJoinController {

    private final UserJoinService userJoinService;

    @Operation(summary="회원가입", description = "회원가입 처리한다.")
    @PostMapping("/join")
    public ResponseEntity<CommonApiResponse> join(@RequestBody @Validated UserJoinCInDto cInDto) {

        UserJoinCOutDto cOutDto = new UserJoinCOutDto();
        UserJoinSInDto sInDto = new UserJoinSInDto();
        BeanUtils.copyProperties(cInDto, sInDto);

        userJoinService.join(sInDto);
        cOutDto.setResult("success");

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }
}
