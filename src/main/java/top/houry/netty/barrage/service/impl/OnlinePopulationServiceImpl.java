package top.houry.netty.barrage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.common.RedisKeyConst;
import top.houry.netty.barrage.service.OnlinePopulationService;
import top.houry.netty.barrage.utils.RedisUtils;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2021/9/1
 **/
@Service
public class OnlinePopulationServiceImpl implements OnlinePopulationService {

    private RedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    @Override
    public void incrementOne() {
        redisUtils.increment(RedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY);
    }

    @Override
    public void decrementOne() {
        redisUtils.decrement(RedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY);
    }
}
