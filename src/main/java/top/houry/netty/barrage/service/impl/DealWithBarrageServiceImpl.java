package top.houry.netty.barrage.service.impl;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DealWithBarrageServiceImpl implements BarrageService {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 处理上送的弹幕信息
     *
     * @param text 弹幕信息
     * @param ctx  通道上下文信息
     */
    @Override
    public void dealWithBarrageMessage(String text, ChannelHandlerContext ctx) {
        log.info("[BarrageServiceImpl]-[dealWithBarrageMessage]-[text:{}]", text);
        NettyServerHandler.CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(text));
        redisUtils.listPush(Const.RedisKey.BARRAGE_REDIS_LIST_KEY, JSONUtil.toJsonStr(new Barrage(null, 1394820394583923485L, new Date(), text, null)));
    }
}
