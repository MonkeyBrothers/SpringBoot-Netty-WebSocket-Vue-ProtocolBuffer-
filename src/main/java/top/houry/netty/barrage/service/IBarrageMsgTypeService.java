package top.houry.netty.barrage.service;

import io.netty.channel.ChannelHandlerContext;
import top.houry.netty.barrage.proto.BarrageProto;

public interface IBarrageMsgTypeService {
    /**
     * 处理上送的弹幕信息
     *
     * @param barrage {@link BarrageProto}
     * @param ctx     通道上下文信息
     */
    void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx);
}
