package top.houry.netty.barrage.service.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.consts.BarrageMsgTypeConst;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.IBarrageMsgTypeService;
import top.houry.netty.barrage.service.IBarrageSendMsgToClientService;
import top.houry.netty.barrage.utils.BarrageConnectInfoUtils;

import java.util.List;

/**
 * @Desc 服务器接收客户端发送过来的弹幕信息
 * @Author houruiyang
 * @Date 2022/1/18
 **/
@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_REQ)
@Slf4j
public class BarrageReceiveMsgServiceImpl implements IBarrageMsgTypeService {

    private IBarrageSendMsgToClientService barrageSendMsgToClientService;

    @Autowired
    public void setBarrageSendMsgToClientService(IBarrageSendMsgToClientService barrageSendMsgToClientService) {
        this.barrageSendMsgToClientService = barrageSendMsgToClientService;
    }

    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientSendBarrageReq clientSendBarrage = BarrageProto.WebClientSendBarrageReq.parseFrom(barrage.getBytesData());
            String msg = StringUtils.isBlank(clientSendBarrage.getMsg()) ? "我们都是追梦人" : clientSendBarrage.getMsg();
            String msgColor = StringUtils.isBlank(clientSendBarrage.getMsgColor()) ? "#FFFFFF" : clientSendBarrage.getMsgColor();
            String userId = StringUtils.isBlank(clientSendBarrage.getUserId()) ? "" : clientSendBarrage.getUserId();
            String videId = StringUtils.isBlank(clientSendBarrage.getVideoId()) ? "" : clientSendBarrage.getVideoId();
            log.info("[Req]-[BarrageReceiveMsgServiceImpl]-[dealWithBarrageMessage]-[msg:{}]-[userId:{}]-[videId:{}]", msg, userId, videId);
            List<ChannelHandlerContext> channelHandlerContextLists = BarrageConnectInfoUtils.getChannelHandlerContextListByVideId(videId);
            if (CollectionUtils.isEmpty(channelHandlerContextLists)) return;
            channelHandlerContextLists.stream().filter(v -> !v.equals(ctx)).forEach(v -> barrageSendMsgToClientService.sendMsg(msg, msgColor, v));
        } catch (Exception e) {
            log.error("[Exception]-[BarrageReceiveMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
