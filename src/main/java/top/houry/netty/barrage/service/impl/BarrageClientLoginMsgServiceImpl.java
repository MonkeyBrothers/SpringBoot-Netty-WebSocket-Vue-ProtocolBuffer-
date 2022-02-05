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
import top.houry.netty.barrage.service.IBarrageOnlinePopulationService;
import top.houry.netty.barrage.service.IBarrageWatchInfoService;
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

    private IBarrageOnlinePopulationService barrageOnlinePopulationService;

    private IBarrageWatchInfoService barrageWatchInfoService;

    @Autowired
    public void setBarrageWatchInfoService(IBarrageWatchInfoService barrageWatchInfoService) {
        this.barrageWatchInfoService = barrageWatchInfoService;
    }

    @Autowired
    public void setBarrageOnlinePopulationService(IBarrageOnlinePopulationService barrageOnlinePopulationService) {
        this.barrageOnlinePopulationService = barrageOnlinePopulationService;
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

            List<String> totalMsgList = redisUtils.listGetAll(BarrageRedisKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.videId);

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            BarrageProto.WebClientLoginResp.Builder loginResp = BarrageProto.WebClientLoginResp.newBuilder();

            loginResp.setBarrageOnlineCount(barrageOnlinePopulationService.getCountByVideoId(videoId));
            loginResp.setBarrageTotalCount(totalMsgList.size());

            builder.setBytesData(loginResp.build().toByteString());
            builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_LOGIN_RESP);

            cacheToRedis(videoId);

            ctx.writeAndFlush(builder);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageClientLoginMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }

    private void cacheToRedis(String videoId) {
        barrageOnlinePopulationService.incrOne(videoId);
        barrageWatchInfoService.addWatchCount(videoId);
    }
}
