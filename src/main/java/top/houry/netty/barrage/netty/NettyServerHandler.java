package top.houry.netty.barrage.netty;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import top.houry.netty.barrage.common.Const;
import top.houry.netty.barrage.utils.ContextUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Desc 配置netty-handler
 * @Author houry
 * @Date 2021/3/2 9:30
 **/
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
//
//    @Autowired
//    private RedisTemplate redisTemplate;

    /**
     * 用于记录和管理所有客户端的channel
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * @param ctx 通道上下文
     * @param msg 信息内容
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        if (!Const.WEBSOCKET_HEARTBEAT_INFO_FLAG.equals(msg.text().trim())) {
            log.info("[NettyServerHandler]-[channelRead0]-[{}]-[recvMsg = {}]",ctx.channel().remoteAddress(), JSONUtil.toJsonStr(msg.text()));
            channels.forEach(v -> v.writeAndFlush(new TextWebSocketFrame(msg.text())));
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
        channels.add(ctx.channel());
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
        channels.remove(ctx.channel());
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


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[NettyServerHandler]-[exceptionCaught]-[Exception]");
        cause.printStackTrace();
        ctx.channel().eventLoop().shutdownGracefully();
        ctx.channel().close();
        channels.remove(ctx.channel());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case READER_IDLE:
                    log.info("[没有接收到：{}的信息心跳信息，将断开连接回收资源]", ctx.toString());
                    ctx.channel().eventLoop().shutdownGracefully();
                    ctx.channel().close();
                    break;
                case WRITER_IDLE:
                    System.out.println("写空闲");
                    break;
                case ALL_IDLE:
                    System.out.println("读写空闲");
                    break;
                default:
                    throw new IllegalStateException("非法状态！");
            }
        }
    }
}
