package iyun.anche.auth.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis服务
 */
@Component
public class RedisStringService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置string值
     * @param key key
     * @param value value
     * @param expire 有效期（秒）
     */
    public void setRedis(String key, String value, Integer expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    public String getRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断某个key是否存在
     */
    public boolean haskey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除某个key
     */
    public boolean delKey(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 设置key的过期时间
     */
    public boolean expire(String key, Integer expire) {
        return redisTemplate.expire(key,expire , TimeUnit.SECONDS);//设
    }
}
