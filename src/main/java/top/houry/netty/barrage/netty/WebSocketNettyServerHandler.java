package top.houry.netty.barrage.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import top.houry.netty.barrage.proto.BarrageProto;
import top.houry.netty.barrage.utils.BarrageConnectInfoUtils;
import top.houry.netty.barrage.utils.BarrageMsgBeanUtils;

/**
 * @Desc 配置netty-handler
 * @Author houry
 * @Date 2021/3/2 9:30
 **/
@Slf4j
public class WebSocketNettyServerHandler extends SimpleChannelInboundHandler<BarrageProto.Barrage> {

    /**
     * 读取发送的消息
     *
     * @param ctx     通道上下文
     * @param barrage 信息内容
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, BarrageProto.Barrage barrage) throws Exception {
        try {
            String msgType = barrage.getMsgType();
            if (StringUtils.isBlank(msgType) || !BarrageMsgBeanUtils.exist(msgType)) {
               log.info("[WebSocketNettyServerHandler]-[channelRead0]-[msgType:{}]-[不存在]", msgType);
                return;
            }
            BarrageMsgBeanUtils.getService(msgType).dealWithBarrageMessage(barrage, ctx);
        } catch (Exception e) {
            ctx.close();
            log.error("[EXCEPTION]-[WebSocketNettyServerHandler]-[channelRead0]", e);
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
        log.info("[WebSocketNettyServerHandler]-[handlerAdded]-[{}]", ctx.channel().toString());
    }

    /**
     * 连接断开的时候触发的操作
     *
     * @param ctx 通道上下文
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("[WebSocketNettyServerHandler]-[handlerRemoved]-[{}]", ctx.channel().toString());
        // 因为会有断网的情况，这里需要把断网的连接信息从缓存中清除
        BarrageConnectInfoUtils.removeChannelInfoByChannelHandlerContext(ctx);
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
     * channel 断开连接的channel
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
        log.error("[WebSocketNettyServerHandler]-[exceptionCaught]-[Exception]", cause);
        ctx.channel().close();
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
                    log.info("[WebSocketNettyServerHandler]-[userEventTriggered]-[没有接收到：{}的信息心跳信息，将断开连接回收资源]", ctx.toString());
                    ctx.channel().close();
                    break;
                case WRITER_IDLE:
                    log.info("[WebSocketNettyServerHandler]-[userEventTriggered]-[写空闲]");
                    break;
                case ALL_IDLE:
                    log.info("[WebSocketNettyServerHandler]-[userEventTriggered]-[读写空闲]");
                    break;
                default:
                    log.info("[WebSocketNettyServerHandler]-[userEventTriggered]-[非法状态]");
                    throw new IllegalStateException("非法状态！");
            }
        }
    }


}
