package top.houry.netty.barrage.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.consts.BarrageRedisKeyConst;
import top.houry.netty.barrage.service.IBarrageWatchInfoService;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/2/5
 **/
@Slf4j
@Service
public class BarrageWatchInfoServiceImpl implements IBarrageWatchInfoService {

    private BarrageRedisUtils barrageRedisUtils;

    @Autowired
    public void setBarrageRedisUtils(BarrageRedisUtils barrageRedisUtils) {
        this.barrageRedisUtils = barrageRedisUtils;
    }

    @Override
    public void addWatchCount(String videoId) {
        barrageRedisUtils.increment(BarrageRedisKeyConst.BARRAGE_TOTAL_WATCH_KEY + videoId);
    }
}
