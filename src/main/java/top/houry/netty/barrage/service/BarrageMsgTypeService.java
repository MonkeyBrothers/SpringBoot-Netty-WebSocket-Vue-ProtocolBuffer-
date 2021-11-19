package top.houry.netty.barrage.service;

import io.netty.channel.ChannelHandlerContext;

public interface BarrageMsgTypeService {
    /**
     * 处理上送的弹幕信息
     *
     * @param text 弹幕信息
     * @param ctx  通道上下文信息
     */
    void dealWithBarrageMessage(String text, ChannelHandlerContext ctx);
}
