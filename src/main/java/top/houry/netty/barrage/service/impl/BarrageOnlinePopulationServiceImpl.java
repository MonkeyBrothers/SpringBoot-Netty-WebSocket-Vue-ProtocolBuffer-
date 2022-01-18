package top.houry.netty.barrage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.common.BarrageRedisKeyConst;
import top.houry.netty.barrage.service.BarrageOnlinePopulationService;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2021/9/1
 **/
@Service
public class BarrageOnlinePopulationServiceImpl implements BarrageOnlinePopulationService {

    private BarrageRedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(BarrageRedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    @Override
    public void incrementOne() {
        redisUtils.increment(BarrageRedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY);
    }

    @Override
    public void decrementOne() {
        redisUtils.decrement(BarrageRedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY);
    }
}
