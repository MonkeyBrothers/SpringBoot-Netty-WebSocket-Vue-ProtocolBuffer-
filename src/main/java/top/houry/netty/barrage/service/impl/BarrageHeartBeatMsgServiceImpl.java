package top.houry.netty.barrage.service.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.consts.BarrageMsgTypeConst;
import top.houry.netty.barrage.consts.BarrageRedisKeyConst;
import top.houry.netty.barrage.consts.BarrageVideoConst;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.IBarrageMsgTypeService;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

import java.util.List;

/**
 * @Desc 心跳包信息
 * @Author houruiyang
 * @Date 2021/8/16
 **/
@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_HEART_BEAT_REQ)
@Slf4j
public class BarrageHeartBeatMsgServiceImpl implements IBarrageMsgTypeService {

    private BarrageRedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(BarrageRedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 处理心跳逻辑
     *
     * @param barrage {@link BarrageProto}
     * @param ctx     通道上下文信息
     */
    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        log.info("[BarrageHeartBeatMsgServiceImpl]-[dealWithBarrageMessage]-[接收到心跳]-[ctx:{}]", ctx.channel().toString());
        BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();

        BarrageProto.WebClientHeartBeatResp.Builder resp = BarrageProto.WebClientHeartBeatResp.newBuilder();
        List<String> totalMsgList = redisUtils.listGetAll(BarrageRedisKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.videId);
        resp.setBarrageTotalCount(totalMsgList.size());

        builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_HEART_BEAT_RESP);
        builder.setBytesData(resp.build().toByteString());
        ctx.writeAndFlush(builder);
    }
}