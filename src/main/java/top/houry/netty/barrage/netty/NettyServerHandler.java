package top.houry.netty.barrage.netty;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Desc 配置netty-handler
 * @Author houry
 * @Date 2021/3/2 9:30
 **/
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private RedisTemplate redisTemplate;

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
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("[NettyServerHandler]-[channelRead0]-[{}]-[recvMsg = {}]",ctx.channel().remoteAddress(), JSONUtil.toJsonStr(msg.text()));
        // 发送之前先保存在redis中
//        redisTemplate.ops
        channels.forEach(v -> v.writeAndFlush(new TextWebSocketFrame(msg.text())));
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
        ctx.channel().close();
        channels.remove(ctx.channel());
    }
}
