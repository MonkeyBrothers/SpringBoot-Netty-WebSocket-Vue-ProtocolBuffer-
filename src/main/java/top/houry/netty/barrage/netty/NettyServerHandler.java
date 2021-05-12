package top.houry.netty.barrage.netty;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.houry.netty.barrage.common.Const;
import top.houry.netty.barrage.service.BarrageService;
import top.houry.netty.barrage.service.impl.BarrageServiceImpl;
import top.houry.netty.barrage.utils.SpringContextUtil;

/**
 * @Desc 配置netty-handler
 * @Author houry
 * @Date 2021/3/2 9:30
 **/
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的channel
     */
    public static final ChannelGroup CLIENT_CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 读取发送的消息
     *
     * @param ctx 通道上下文
     * @param msg 信息内容
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        if (!Const.WEBSOCKET_HEARTBEAT_INFO_FLAG.equals(msg.text().trim())) {
            log.info("[NettyServerHandler]-[channelRead0]-[{}]-[recvMsg = {}]", ctx.channel().remoteAddress(), JSONUtil.toJsonStr(msg.text()));
            CLIENT_CHANNELS.writeAndFlush(new TextWebSocketFrame(msg.text()));
            BarrageService barrageService = SpringContextUtil.getBean("barrageService");
            barrageService.dealWithBarrageMessage(msg.text());
        }
    }

    /**
     * 有新的连接上来的时候触发的操作
     *
     * @param ctx 通道上下文
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("[{}]-[handlerAdded]", ctx.channel().remoteAddress().toString());
        CLIENT_CHANNELS.add(ctx.channel());
    }

    /**
     * 连接断开的时候触发的操作
     *
     * @param ctx 通道上下文
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("[{}]-[handlerRemoved]", ctx.channel().remoteAddress().toString());
        CLIENT_CHANNELS.remove(ctx.channel());
    }

    /**
     * 连接被激活的时候触发的操作
     *
     * @param ctx 通道上下文
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * channel 不活跃
     *
     * @param ctx 通道上下文
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * 异常信息捕获
     *
     * @param ctx   通道上下文
     * @param cause 异常信息
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[NettyServerHandler]-[exceptionCaught]-[Exception]");
        cause.printStackTrace();
        ctx.channel().close();
        CLIENT_CHANNELS.remove(ctx.channel());
    }


    /**
     * 心跳检测事件
     *
     * @param ctx 通道上下文
     * @param evt 触发事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case READER_IDLE:
                    log.info("[没有接收到：{}的信息心跳信息，将断开连接回收资源]", ctx.toString());
                    ctx.channel().close();
                    break;
                case WRITER_IDLE:
                    log.info("写空闲");
                    break;
                case ALL_IDLE:
                    log.info("读写空闲");
                    break;
                default:
                    log.info("非法状态");
                    throw new IllegalStateException("非法状态！");
            }
        }
    }



}
