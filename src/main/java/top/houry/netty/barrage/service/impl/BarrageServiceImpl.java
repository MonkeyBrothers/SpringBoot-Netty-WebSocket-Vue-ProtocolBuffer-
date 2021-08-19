package top.houry.netty.barrage.service.impl;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.common.Const;
import top.houry.netty.barrage.netty.NettyServerHandler;
import top.houry.netty.barrage.pojo.Barrage;
import top.houry.netty.barrage.service.BarrageService;
import top.houry.netty.barrage.utils.RedisUtils;
import top.houry.netty.barrage.utils.XssUtil;

import java.util.Date;

/**
 * @Desc 弹幕消息处理
 * @Author houry
 * @Date 2021/5/12 10:28
 **/
@Service
public class BarrageServiceImpl implements BarrageService {

    /**
     * 视频id
     * <p>目前就一个video，这里暂时模拟一个id</p>
     */
    private static final String VIDEO_ID = "2222222222";


    @Autowired
    private RedisUtils redisUtils;

    /**
     * 处理上送的弹幕信息
     *
     * @param text 弹幕信息
     */
    @Override
    public void dealWithBarrageMessage(String text) {
        NettyServerHandler.CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(text));
        System.out.println(text);
    }
}
