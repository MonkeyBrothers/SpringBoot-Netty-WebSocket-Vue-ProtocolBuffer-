package top.houry.netty.barrage.service.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.consts.BarrageMsgTypeConst;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.IBarrageMsgTypeService;
import top.houry.netty.barrage.service.IBarrageOnlinePopulationService;
import top.houry.netty.barrage.utils.BarrageConnectInfoUtils;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/22
 **/
@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_LOGOUT_REQ)
@Slf4j
public class BarrageClientLogoutMsgServiceImpl implements IBarrageMsgTypeService {

    private IBarrageOnlinePopulationService barrageOnlinePopulationService;

    @Autowired
    public void setBarrageOnlinePopulationService(IBarrageOnlinePopulationService barrageOnlinePopulationService) {
        this.barrageOnlinePopulationService = barrageOnlinePopulationService;
    }

    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientLogoutReq loginInfo = BarrageProto.WebClientLogoutReq.parseFrom(barrage.getBytesData());
            String userId = StringUtils.isBlank(loginInfo.getUserId()) ? "" : loginInfo.getUserId();
            String videoId = StringUtils.isBlank(loginInfo.getVideoId()) ? "" : loginInfo.getVideoId();
            log.info("[Req]-[BarrageClientLogoutMsgServiceImpl]-[dealWithBarrageMessage]-[userId:{}]-[videoId:{}]", userId, videoId);
            BarrageConnectInfoUtils.removeChannelInfoFromBaseMap(videoId, ctx);
            barrageOnlinePopulationService.decrOne(videoId);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageClientLogoutMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
