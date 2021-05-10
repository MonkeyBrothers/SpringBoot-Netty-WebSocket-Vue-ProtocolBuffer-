package top.houry.netty.barrage.scheduled;

import cn.hutool.json.JSONUtil;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.houry.netty.barrage.netty.NettyServer;
import top.houry.netty.barrage.netty.NettyServerHandler;
import top.houry.netty.barrage.utils.ContextUtil;

/**
 * @Desc 为所有的连接客户端推送历史弹幕信息
 * @Author houry
 * @Date 2021/5/10 15:05
 **/
@Component
@Slf4j
public class BarrageScheduling {

    /**
     * 定时任务推送历史弹幕
     */
    @Scheduled(fixedRate = 3000)
    public void pushHistoryBarrage() {
        NettyServerHandler.CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(ContextUtil.getContext()));
    }

}
