package top.houry.netty.barrage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestWebSocketNettyClientHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(TestWebSocketNettyClientHandler.class);

    private WebSocketClientHandshaker handShaker = null;
    private ChannelPromise handshakeFuture = null;




    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.handshakeFuture = ctx.newPromise();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (!this.handShaker.isHandshakeComplete()) {
            FullHttpResponse response = (FullHttpResponse) msg;
            this.handShaker.finishHandshake(ctx.channel(), response);
            this.handshakeFuture.setSuccess();
            return;
        }

        if (msg instanceof BinaryWebSocketFrame) {
            ByteBuf buf = ((BinaryWebSocketFrame) msg).content();
            logger.info("WebSocketClientHandler::channelRead0 BinaryWebSocketFrame:" + buf.toString(CharsetUtil.UTF_8));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("连接了...........");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("WebSocketClientHandler::channelInactive 服务端连接断开");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
    }

    public void setHandShaker(WebSocketClientHandshaker handShaker) {
        this.handShaker = handShaker;
    }

    public ChannelFuture handshakeFuture() {
        return this.handshakeFuture;
    }

}