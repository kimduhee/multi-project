package com.framework.web.controller.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SampleDeleteCInDto {

    @Schema(description = "샘플키값", example = "1")
    @NotEmpty(message="키값이 존재하지 않습니다.")
    private String sampleId;
}
