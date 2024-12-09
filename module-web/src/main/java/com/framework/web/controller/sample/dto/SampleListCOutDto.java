package com.framework.web.controller.sample.dto;

import com.framework.web.service.sample.dto.GetSampleListSOutDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SampleListCOutDto {
    private List<GetSampleListSOutDto> sampleList;
}
