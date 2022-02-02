package top.houry.netty.barrage.service.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.consts.BarrageMsgTypeConst;
import top.houry.netty.barrage.consts.BarrageRedisKeyConst;
import top.houry.netty.barrage.consts.BarrageVideoConst;
import top.houry.netty.barrage.entity.BarrageColorConfigure;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.IBarrageColorConfigureService;
import top.houry.netty.barrage.service.IBarrageMsgTypeService;
import top.houry.netty.barrage.utils.BarrageConnectInfoUtils;
import top.houry.netty.barrage.utils.BarrageContentUtils;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/22
 **/
@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_LOGIN_REQ)
@Slf4j
public class BarrageClientLoginMsgServiceImpl implements IBarrageMsgTypeService {

    private BarrageRedisUtils redisUtils;

    private IBarrageColorConfigureService barrageColorConfigureService;

    @Autowired
    public void setBarrageColorConfigureService(IBarrageColorConfigureService barrageColorConfigureService) {
        this.barrageColorConfigureService = barrageColorConfigureService;
    }

    @Autowired
    public void setRedisUtils(BarrageRedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientLoginReq loginInfo = BarrageProto.WebClientLoginReq.parseFrom(barrage.getBytesData());
            String userId = StringUtils.isBlank(loginInfo.getUserId()) ? "" : loginInfo.getUserId();
            String videoId = StringUtils.isBlank(loginInfo.getVideoId()) ? "" : loginInfo.getVideoId();
            log.info("[Req]-[BarrageClientLoginMsgServiceImpl]-[dealWithBarrageMessage]-[userId:{}]-[videoId:{}]", userId, videoId);
            BarrageConnectInfoUtils.addChannelInfoToBaseMap(videoId, ctx);

            List<String> colors = barrageColorConfigureService.getAll().stream().map(BarrageColorConfigure::getCode).collect(Collectors.toList());
            List<String> totalMsgList = redisUtils.listGetAll(BarrageRedisKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.videId);

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            BarrageProto.WebClientLoginResp.Builder loginResp = BarrageProto.WebClientLoginResp.newBuilder();

            loginResp.addAllMsgColorList(colors);
            loginResp.setBarrageTotalCount(totalMsgList.size());

            builder.setBytesData(loginResp.build().toByteString());
            builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_LOGIN_RESP);

            ctx.writeAndFlush(builder);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageClientLoginMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
