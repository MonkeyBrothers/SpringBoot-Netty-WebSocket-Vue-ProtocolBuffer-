package top.houry.netty.barrage.service.impl;

import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.IBarrageMsgTypeService;

/**
 * @Desc 服务器接收客户端发送过来的弹幕信息
 * @Author houruiyang
 * @Date 2022/1/18
 **/
@Service
@BarrageAnnotation(msgType = "web.client.barrage")
@Slf4j
public class BarrageReceiveMsgServiceImpl implements IBarrageMsgTypeService {
    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientSendBarrage clientSendBarrage = BarrageProto.WebClientSendBarrage.parseFrom(barrage.getBytesData());
            String msg = StringUtils.isBlank(clientSendBarrage.getMsg()) ? "" : clientSendBarrage.getMsg();
            String msgColor = StringUtils.isBlank(clientSendBarrage.getMsgColor()) ? "" : clientSendBarrage.getMsgColor();
            String userId = StringUtils.isBlank(clientSendBarrage.getUserId()) ? "" : clientSendBarrage.getUserId();
            String videId = StringUtils.isBlank(clientSendBarrage.getVideoId()) ? "" : clientSendBarrage.getVideoId();
            log.info("[Req]-[BarrageReceiveMsgServiceImpl]-[dealWithBarrageMessage]-[msg:{}]-[userId:{}]-[videId:{}]", msg, userId, videId);
            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();

            BarrageProto.WebClientSendBarrage.Builder client = BarrageProto.WebClientSendBarrage.newBuilder();
            client.setMsg("这是来自服务端的回复");
            client.setUserId("0000000000");
            client.setMsgColor("#21141435");
            client.setVideoId("5321423454532535");
            builder.setMsgType("web.client.barrage");
            builder.setBytesData(client.build().toByteString());

            ctx.writeAndFlush(builder).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("success");
                } else {
                    System.out.println(future.cause().getMessage());
                }
            });

        } catch (Exception e) {
            log.error("[Exception]-[BarrageReceiveMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
