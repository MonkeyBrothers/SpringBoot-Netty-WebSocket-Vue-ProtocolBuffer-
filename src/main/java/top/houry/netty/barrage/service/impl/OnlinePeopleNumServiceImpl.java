package top.houry.netty.barrage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.common.Const;
import top.houry.netty.barrage.service.OnlinePeopleNumService;
import top.houry.netty.barrage.utils.RedisUtils;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2021/9/1
 **/
@Service
public class OnlinePeopleNumServiceImpl implements OnlinePeopleNumService {
    @Autowired
    private RedisUtils redisUtils;


    @Override
    public void incrementOne() {
        redisUtils.increment(Const.RedisKey.BARRAGE_ONLINE_POPULATION_KEY);
    }

    @Override
    public void decrementOne() {
        redisUtils.decrement(Const.RedisKey.BARRAGE_ONLINE_POPULATION_KEY);
    }
}
