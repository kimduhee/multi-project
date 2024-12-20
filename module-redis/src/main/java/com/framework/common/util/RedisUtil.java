package com.framework.common.util;

import com.framework.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;

import java.util.List;
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
public class RedisUtil<T> {

    /**
     * key에 대한 String 값 저장
     * @param key
     * @param value
     * @throws Exception
     */
    public static <T> void setRedisStringData(String key, T value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
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
    public static <T> T getRedisStringData(String key) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
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
    public static <T> void deleteAllRedisStirngData(String key) {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            redisTemplate.delete(key + ":string");
        } catch(Exception e) {
            log.error("deleteAllRedisStirngData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 List 값 저장(기존 데이터 뒤에 저장)
     * @param key
     * @param value
     * @param <T>
     * @throws Exception
     */
    public static <T> void setRedisListRightPushData(String key, T value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ListOperations<String, T> listOperations = redisTemplate.opsForList();
            listOperations.rightPush(key + ":list", value);
        } catch(Exception e) {
            log.error("setRedisListRightPushData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 List 값 저장(기존 데이터 앞에 저장)
     * @param key
     * @param value
     * @param <T>
     * @throws Exception
     */
    public static <T> void setRedisListLeftPushData(String key, T value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ListOperations<String, T> listOperations = redisTemplate.opsForList();
            listOperations.leftPush(key + ":list", value);
        } catch(Exception e) {
            log.error("setRedisListLeftPushData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 List index에 해당하는 데이터 값을 변경
     * @param key
     * @param index
     * @param value
     * @param <T>
     * @throws Exception
     */
    public static <T> void setRedisListUpdateData(String key, int index, T value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ListOperations<String, T> listOperations = redisTemplate.opsForList();
            listOperations.set(key + ":list", index, value);
        } catch(Exception e) {
            log.error("setRedisListUpdateData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 List 단건 조회
     * @param key
     * @param index
     * @return
     * @param <T>
     * @throws Exception
     */
    public static <T> T getOneRedisListData(String key, int index) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ListOperations<String, T> listOperations = redisTemplate.opsForList();
            return listOperations.index(key + ":list", index);
        } catch(Exception e) {
            log.error("getOneRedisListData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 List 범위값 조회
     * @param key
     * @param start
     * @param end
     * @return
     * @param <T>
     * @throws Exception
     */
    public static <T> List<T> getRangeRedisListData(String key, int start, int end) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ListOperations<String, T> listOperations = redisTemplate.opsForList();
            return listOperations.range(key + ":list", start, end);
        } catch(Exception e) {
            log.error("getRangeRedisListData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 List size 조회
     * @param key
     * @return
     * @param <T>
     * @throws Exception
     */
    public static <T> Long getSizeRedisListData(String key) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            ListOperations<String, T> listOperations = redisTemplate.opsForList();

            return listOperations.size(key + ":list");
        } catch(Exception e) {
            log.error("getSizeRedisListData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 list 모든값 삭제
     * @param key
     * @param <T>
     */
    public static <T> void deleteAllRedisListData(String key) {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            redisTemplate.delete(key + ":list");
        } catch(Exception e) {
            log.error("deleteAllRedisListData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Set 값 저장
     * @param key
     * @param value
     * @throws Exception
     */
    public static <T> void setRedisSetData(String key, T... value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, T> setOperations = redisTemplate.opsForSet();
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
    public static <T> Set<T> getRedisSetData(String key) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, T> setOperations = redisTemplate.opsForSet();
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
    public static <T> boolean isExistsRedisSetData(String key, T value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, T> setOperations = redisTemplate.opsForSet();
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
    public static <T> void deleteOneRedisSetData(String key, T value) {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            SetOperations<String, T> setOperations = redisTemplate.opsForSet();
            setOperations.remove(key + ":set", value);
        } catch(Exception e) {
            log.error("deleteOneRedisSetData exception :: ", e);
            throw new BizException(e);
        }
    }

    /**
     * key에 대한 Set 모든값 삭제
     * @param key
     */
    public static <T> void deleteAllRedisSetData(String key) {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
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
    public static <T> void setRedisHashData(String key, String field, T value) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
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
    public static <T> Map<String, T> getRedisHashData(String key) throws Exception {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
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
    public static <T> void deleteAllRedisHashData(String key) {
        try {
            RedisTemplate<String, T> redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
            redisTemplate.delete(key + ":hash");
        } catch(Exception e) {
            log.error("deleteAllRedisHashData exception :: ", e);
            throw new BizException(e);
        }
    }
}
