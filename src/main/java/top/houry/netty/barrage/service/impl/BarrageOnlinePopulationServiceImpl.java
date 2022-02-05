package top.houry.netty.barrage.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.consts.BarrageRedisKeyConst;
import top.houry.netty.barrage.service.IBarrageOnlinePopulationService;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2021/9/1
 **/
@Service
public class BarrageOnlinePopulationServiceImpl implements IBarrageOnlinePopulationService {

    private BarrageRedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(BarrageRedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    @Override
    public void incrOne(String videoId) {
        redisUtils.increment(BarrageRedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId);
    }

    @Override
    public void decrOne(String videoId) {
        redisUtils.decrement(BarrageRedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId);
    }

    @Override
    public int getCountByVideoId(String videoId) {
        String onlineCount = redisUtils.get(BarrageRedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId);
        return StringUtils.isBlank(onlineCount) ? 0 : Integer.parseInt(onlineCount);
    }
}
