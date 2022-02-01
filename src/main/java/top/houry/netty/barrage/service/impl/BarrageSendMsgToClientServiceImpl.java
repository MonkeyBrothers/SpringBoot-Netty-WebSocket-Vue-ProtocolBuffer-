package top.houry.netty.barrage.service.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.houry.netty.barrage.consts.BarrageMsgTypeConst;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.service.IBarrageSendMsgToClientService;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/2/2
 **/
@Service
@Slf4j
public class BarrageSendMsgToClientServiceImpl implements IBarrageSendMsgToClientService {
    @Override
    public void sendMsg(String msg, String msgColor, ChannelHandlerContext context) {
        BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
        BarrageProto.WebClientSendBarrageResp.Builder client = BarrageProto.WebClientSendBarrageResp.newBuilder();
        client.setMsg(msg);
        client.setMsgColor(StringUtils.isBlank(msgColor) ? "#fff" : msgColor);
        builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_RESP);
        builder.setBytesData(client.build().toByteString());
        context.writeAndFlush(builder);
    }
}
