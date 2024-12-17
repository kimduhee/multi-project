package com.framework.common.security.controller;

import com.framework.common.handler.CommonApiResponse;
import com.framework.common.security.controller.dto.JoinCInDto;
import com.framework.common.security.controller.dto.JoinCOutDto;
import com.framework.common.security.service.JoinService;
import com.framework.common.security.service.dto.JoinSInDto;
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
public class JoinController {

    private final JoinService userJoinService;

    @Operation(summary="회원가입", description = "회원가입 처리한다.")
    @PostMapping("/join")
    public ResponseEntity<CommonApiResponse> join(@RequestBody @Validated JoinCInDto cInDto) {

        JoinCOutDto cOutDto = new JoinCOutDto();
        JoinSInDto sInDto = new JoinSInDto();
        BeanUtils.copyProperties(cInDto, sInDto);

        int result  = userJoinService.join(sInDto);
        if(result > 0) {
            cOutDto.setResult("success");
        }

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }
}
