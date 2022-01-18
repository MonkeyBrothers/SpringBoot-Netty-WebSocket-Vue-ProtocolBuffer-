package top.houry.netty.barrage.service.impl;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.BarrageMsgTypeService;

/**
 * @Desc 心跳包信息
 * @Author houruiyang
 * @Date 2021/8/16
 **/
@Service
@BarrageAnnotation(msgType = "heartBeat")
public class BarrageHeartBeatMsgServiceImpl implements BarrageMsgTypeService {
    /**
     * 处理心跳逻辑
     *
     * @param barrage {@link BarrageProto}
     * @param ctx     通道上下文信息
     */
    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        System.out.println("-----");
        // 如果想对心跳进行回复，可以在此方法中进行回复
//        ctx.writeAndFlush("").addListener((ChannelFutureListener) future -> {
//
//        });
    }
}
