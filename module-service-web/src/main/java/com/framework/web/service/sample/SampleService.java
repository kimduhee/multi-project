package com.framework.web.service.sample;

import com.framework.web.mapper.sample.SampleMapper;
import com.framework.web.service.sample.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SampleService {

    private final SampleMapper sampleMapper;

    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public List<GetSampleListSOutDto> getSampleList(GetSampleListSInDto getSampleListSInDto) {
        return sampleMapper.getSampleList(getSampleListSInDto);
    }

    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public GetSampleSOutDto getSample(GetSampleSInDto getSampleSInDto) {
        return sampleMapper.getSample(getSampleSInDto);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public int saveSample(SaveSampleSInDto saveSampleSInDto) {
        return sampleMapper.saveSample(saveSampleSInDto);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public int updateSample(UpdateSampleSInDto updateSampleSInDto) {
        return sampleMapper.updateSample(updateSampleSInDto);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public int deleteSample(DeleteSampleSInDto deleteSampleSInDto) {
        return sampleMapper.deleteSample(deleteSampleSInDto);
    }
}
