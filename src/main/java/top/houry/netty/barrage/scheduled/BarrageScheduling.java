package top.houry.netty.barrage.scheduled;

import cn.hutool.json.JSONUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import top.houry.netty.barrage.common.BarrageRedisKeyConst;
import top.houry.netty.barrage.netty.WebSocketNettyServerHandler;
import top.houry.netty.barrage.pojo.Barrage;
import top.houry.netty.barrage.utils.BarrageContentUtils;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Desc 为所有的连接客户端推送历史弹幕信息
 * @Author houry
 * @Date 2021/5/10 15:05
 **/
//@Component
@Slf4j
public class BarrageScheduling {
    @Autowired
    private BarrageRedisUtils redisUtils;

    /**
     * 定时任务推送历史弹幕 fixedRate = 3000  3秒推送一次
     */
    @Scheduled(fixedRate = 5000)
    public void pushHistoryBarrage() {
        WebSocketNettyServerHandler.CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(BarrageContentUtils.getContext(Arrays.asList(BarrageContentUtils.context))));
        List<String> barrageInfoList = redisUtils.listGetAll(BarrageRedisKeyConst.BARRAGE_REDIS_LIST_KEY);
        WebSocketNettyServerHandler.CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(JSONUtil.toBean(BarrageContentUtils.getContext(barrageInfoList), Barrage.class).getMsg()));
    }

    /**
     * 推送在线人数
     */
    @Scheduled(fixedRate = 5000)
    public void pushOnlineNum() {
        String onlinePopulation = redisUtils.get(BarrageRedisKeyConst.BARRAGE_ONLINE_POPULATION_KEY);
        WebSocketNettyServerHandler.CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(onlinePopulation));
    }

}
