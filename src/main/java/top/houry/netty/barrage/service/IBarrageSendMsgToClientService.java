package top.houry.netty.barrage.service;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/2/2
 **/
public interface IBarrageSendMsgToClientService {
    void sendMsg(String msg, String msgColor, ChannelHandlerContext context);
}
