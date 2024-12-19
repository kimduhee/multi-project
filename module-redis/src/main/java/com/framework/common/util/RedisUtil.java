package com.framework.common.util;

import com.framework.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.Set;

/**
 * packageName    : com.framework.common.util
 * fileName       : RedisUtil
 * author         : NAMANOK
 * date           : 2024-12-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-19        NAMANOK       최초 생성
 */
@Slf4j
public class RedisUtil {

    /**
     * key에 대한 String 값 저장
     * @param key
     * @param value
     * @throws Exception
     */
    public static void setRedisStringData(String key, String value) throws Exception {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key + ":string", value);
        } catch(Exception e) {
            log.error("setRedisStringData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Stirng 값 조회
     * @param key
     * @return
     * @throws Exception
     */
    public static String getRedisStringData(String key) throws Exception {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            return valueOperations.get(key + ":string");
        } catch(Exception e) {
            log.error("getRedisStringData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 String 값 모든값 삭제
     * @param key
     */
    public static void deleteAllRedisStirngData(String key) {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            redisTemplate.delete(key + ":string");
        } catch(Exception e) {
            log.error("deleteAllRedisStirngData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Set 값 저장
     * @param key
     * @param value
     * @throws Exception
     */
    public static void setRedisSetData(String key, String... value) throws Exception {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            setOperations.add(key+":set", value);
        } catch(Exception e) {
            log.error("setRedisSetData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Set 값 조회
     * @param key
     * @return
     * @throws Exception
     */
    public static Set<String> getRedisSetData(String key) throws Exception {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            return setOperations.members(key + ":set");
        } catch(Exception e) {
            log.error("getRedisSetData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     *  key에 대한 Set 값에 존재하는지 조회
     *  (true: 데이터 있음, false: 데이터 없음)
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public static boolean isExistsRedisSetData(String key, String value) throws Exception {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            return setOperations.isMember(key + ":set", value);
        } catch(Exception e) {
            log.error("isExistsRedisSetData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Set 해당값 삭제
     * @param key
     */
    public static void deleteOneRedisSetData(String key, String value) {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            setOperations.remove(key + ":set", value);
        } catch(Exception e) {
            log.error("deleteAllRedisSetData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Set 모든값 삭제
     * @param key
     */
    public static void deleteAllRedisSetData(String key) {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            redisTemplate.delete(key + ":set");
        } catch(Exception e) {
            log.error("deleteAllRedisSetData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 hash 값 저장
     * @param key
     * @param field
     * @param value
     * @throws Exception
     */
    public static void setRedisHashData(String key, String field, String value) throws Exception {
        try {
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(key + ":hash", field, value);
        } catch(Exception e) {
            log.error("setRedisHashData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 hash 값 조회
     * @param key
     * @return
     * @throws Exception
     */
    public static Map<String, String> getRedisHashData(String key) throws Exception {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            return hashOperations.entries(key + ":hash");
        } catch(Exception e) {
            log.error("getRedisHashData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 hash 모든값 삭제
     * @param key
     */
    public static void deleteAllRedisHashData(String key) {
        try {
            RedisTemplate<String, String> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            redisTemplate.delete(key + ":hash");
        } catch(Exception e) {
            log.error("deleteAllRedisHashData exception :: ", e);
            throw new BizException(e);
        }
    }
}
