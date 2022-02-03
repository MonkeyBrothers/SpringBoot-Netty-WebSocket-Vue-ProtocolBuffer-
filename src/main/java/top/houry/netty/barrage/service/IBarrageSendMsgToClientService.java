package top.houry.netty.barrage.service;

import io.netty.channel.ChannelHandlerContext;
import top.houry.netty.barrage.bo.BarrageMsgBo;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/2/2
 **/
public interface IBarrageSendMsgToClientService {
    void sendMsg(BarrageMsgBo barrageMsgBo, ChannelHandlerContext context);
}
