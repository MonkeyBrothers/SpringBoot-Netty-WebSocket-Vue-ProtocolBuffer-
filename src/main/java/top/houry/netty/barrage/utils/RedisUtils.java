package top.houry.netty.barrage.utils;

import org.springframework.beans.factory.annotation.Autowired;
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
    private StringRedisTemplate stringRedisTemplate;


    public boolean set(String key, Object value) {
        stringRedisTemplate.opsForValue();
        return true;
    }

}
