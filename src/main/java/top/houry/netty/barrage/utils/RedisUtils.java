package top.houry.netty.barrage.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Desc redis 工具类
 * @Author houry
 * @Date 2021/3/29 14:10
 **/
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存放数据集合
     *
     * @param key   数据key
     * @param value 数据值
     * @return 返回存放的位置
     */
    public long listPush(String key, String value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0L : count;
    }

    /**
     * 存放hash数据
     *
     * @param key   key
     * @param hKey  hKey
     * @param value value
     */
    public void hashPut(String key, String hKey, String value) {
        this.redisTemplate.opsForHash().put(key, hKey, value);
    }

}
