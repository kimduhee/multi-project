package com.framework.web.controller.sample;

import com.framework.common.handler.CommonApiResponse;
import com.framework.common.util.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Tag(name = "Redis API", description = "Redis 테스트용 API")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/v1/api/redis")
@RestController
public class RedisController {

    @PostMapping(value="/redis-test")
    @Operation(summary="redis test", description = "redis test")
    public ResponseEntity<CommonApiResponse> redisTest() {

        try {
            RedisUtil.deleteAllRedisStirngData("test");
            RedisUtil.deleteAllRedisSetData("test");
            RedisUtil.deleteAllRedisHashData("test");

            log.debug("***********************************************");
            log.debug("* String test !!");
            log.debug("***********************************************");
            log.debug("- Insert test => key : test | value : 1234");
            RedisUtil.setRedisStringData("test","1234");
            log.debug("- Redis result :: {}", RedisUtil.getRedisStringData("test"));
            log.debug("- 초기값");

            log.debug("-----------------------------------------------");
            log.debug("- Update test => key : test | value : 5678");
            RedisUtil.setRedisStringData("test","5678");
            log.debug("- Redis result :: {}", RedisUtil.getRedisStringData("test"));
            log.debug("- 값 변경됨");

            log.debug("-----------------------------------------------");
            log.debug("- 전체 Delete test => key : test");
            RedisUtil.deleteAllRedisStirngData("test");
            log.debug("- Redis result :: {}", RedisUtil.getRedisStringData("test"));
            log.debug("- 값 전체 삭제됨");

            log.debug("***********************************************");
            log.debug("* Set test !!");
            log.debug("***********************************************");
            log.debug("- Insert test => key : test | value : 1234, 5678");
            RedisUtil.setRedisSetData("test","1234","5678");
            Set<String> setList = RedisUtil.getRedisSetData("test");
            for(String txt: setList) {
                log.debug("- Redis result :: {}", txt);
            }
            log.debug("- 초기값");

            log.debug("-----------------------------------------------");
            log.debug("- Insert test => key : test | value : 1111, 2222");
            RedisUtil.setRedisSetData("test","1111","2222");
            setList = RedisUtil.getRedisSetData("test");
            for(String txt: setList) {
                log.debug("- Redis result :: {}", txt);
            }
            log.debug("- 값 추가됨");

            log.debug("-----------------------------------------------");
            log.debug("- Duplication insert test => key : test | value : 1234, 1111");
            RedisUtil.setRedisSetData("test","1234","1111");
            setList = RedisUtil.getRedisSetData("test");
            for(String txt: setList) {
                log.debug("- Redis result :: {}", txt);
            }
            log.debug("- 동일값 추가안됨");

            log.debug("-----------------------------------------------");
            log.debug("- Data 존재여부 test => key : test | value : 1234");
            log.debug("- Redis result :: {}", RedisUtil.isExistsRedisSetData("test","1234"));
            log.debug("- 값 있음 확인");

            log.debug("-----------------------------------------------");
            log.debug("- Data 존재여부 test => key : test | value : 9999");
            log.debug("- Redis result :: {}", RedisUtil.isExistsRedisSetData("test","9999"));
            log.debug("- 값 없음 확인");

            log.debug("-----------------------------------------------");
            log.debug("- 일부 Delete test => key : test | value : 5678");
            RedisUtil.deleteOneRedisSetData("test","5678");
            setList = RedisUtil.getRedisSetData("test");
            for(String txt: setList) {
                log.debug("- Redis result :: {}", txt);
            }
            log.debug("- 동일값 추가안됨");

            log.debug("-----------------------------------------------");
            log.debug("- 전체 Delete test => key : test");
            RedisUtil.deleteAllRedisSetData("test");
            setList = RedisUtil.getRedisSetData("test");
            for(String txt: setList) {
                log.debug("- Redis result :: {}", txt);
            }
            log.debug("- 값 전체 삭제됨");

            log.debug("***********************************************");
            log.debug("* Hash test !!");
            log.debug("***********************************************");
            log.debug("- Insert test => key : test | value : a=1, b=2");
            RedisUtil.setRedisHashData("test","a","1");
            RedisUtil.setRedisHashData("test","b","2");
            Map<String, String> hashList = RedisUtil.getRedisHashData("test");
            Set<String> ketset =  hashList.keySet();
            for(String txt: ketset) {
                log.debug("- Redis result :: key={}, value={}", txt, hashList.get(txt).toString());
            }
            log.debug("- 초기값");

            log.debug("-----------------------------------------------");
            log.debug("- Insert test => key : test | value : c=3, d=4");
            RedisUtil.setRedisHashData("test","c","3");
            RedisUtil.setRedisHashData("test","d","4");
            hashList = RedisUtil.getRedisHashData("test");
            ketset =  hashList.keySet();
            for(String txt: ketset) {
                log.debug("- Redis result :: key={}, value={}", txt, hashList.get(txt).toString());
            }
            log.debug("- 값 추가됨");

            log.debug("-----------------------------------------------");
            log.debug("- Update test => key : test | value : b=1, d=3");
            RedisUtil.setRedisHashData("test","b","1");
            RedisUtil.setRedisHashData("test","d","3");
            hashList = RedisUtil.getRedisHashData("test");
            ketset =  hashList.keySet();
            for(String txt: ketset) {
                log.debug("- Redis result :: key={}, value={}", txt, hashList.get(txt).toString());
            }
            log.debug("- 동일 값 변경됨");

            log.debug("-----------------------------------------------");
            log.debug("- 전체 Delete test => key : test");
            RedisUtil.deleteAllRedisHashData("test");
            hashList = RedisUtil.getRedisHashData("test");
            ketset =  hashList.keySet();
            for(String txt: ketset) {
                log.debug("- Redis result :: key={}, value={}", txt, hashList.get(txt).toString());
            }
            log.debug("- 값 전체 삭제됨");

        } catch(Exception e) {

            log.error("redis error : ", e);
        }
        return new ResponseEntity<>(CommonApiResponse.ok("success"), HttpStatus.OK);
    }
}
