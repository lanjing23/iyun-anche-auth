package iyun.anche.auth.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis服务
 */
@Component
public class RedisHashService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置hash值
     * @param hashkey key
     * @param map map对象
     * @param expire 有效期（秒）
     */
    public void setRedis(String hashkey, Map<String, String> map, Integer expire) {
        redisTemplate.opsForHash().putAll(hashkey, map);
        redisTemplate.expire(hashkey, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置hash值
     * @param hashkey key
     * @param map map对象
     */
    public void setRedis(String hashkey, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(hashkey, map);
    }

    /**
     * 设置hash某个对象值
     * @param hashkey key
     * @param hk hash对象中的key
     * @param hv hash对象中的value
     * @param expire 有效期（秒）
     */
    public void setRedis(String hashkey, String hk, String hv, Integer expire) {
        redisTemplate.opsForHash().put(hashkey, hk, hv);
        redisTemplate.expire(hashkey, expire, TimeUnit.SECONDS);
    }
    /**
     * 设置hash某个对象值
     * @param hashkey key
     * @param hk hash对象中的key
     * @param hv hash对象中的value
     */
    public void setRedis(String hashkey, String hk, String hv) {
        redisTemplate.opsForHash().put(hashkey, hk, hv);
    }

    /**
     * 获取缓存对象
     * @param hashkey redis key
     * @return 对象json格式
     */
    public Map<String, String> getObj(String hashkey) {
        Map list = redisTemplate.opsForHash().entries(hashkey);
        return list;
    }

    /**
     * 获取对象字段数量
     * @param hashkey redis key
     * @return 字段数量
     */
    public Long getObjSize(String hashkey) {
        return redisTemplate.opsForHash().size(hashkey);
    }

    /**
     * 获取对象所有key值
     * @param hashkey redis key
     * @return key值json格式
     */
    public Collection<Object> getObjKeys(String hashkey) {
        Collection list = redisTemplate.opsForHash().keys(hashkey);
        return list;
    }

    /**
     * 获取对象key字段对应的值
     * @param hashkey redis key
     * @param hk hash key
     * @return hash value
     */
    public String getObjKeyValue(String hashkey, String hk) {
        return redisTemplate.opsForHash().get(hashkey, hk).toString();
    }

    /**
     * 获取对象多个key字段对应的值
     * @param hashkey redis key
     * @param hk hash key
     * @return hash value
     */
    public List getObjKeyValue(String hashkey, List<Object> hk) {
        List<Object> list = redisTemplate.opsForHash().multiGet(hashkey, hk);
        return list;
    }

    /**
     * 判断hash对象某个key是否存在
     * @param hashkey redis key
     * @param hk hash key
     * @return 存在返回true，不存在返回false
     */
    public boolean haskey(String hashkey, String hk) {
        return redisTemplate.opsForHash().hasKey(hashkey, hk);
    }
}
