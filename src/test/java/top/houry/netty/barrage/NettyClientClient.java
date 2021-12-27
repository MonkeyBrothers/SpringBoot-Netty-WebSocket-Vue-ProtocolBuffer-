package top.houry.netty.barrage;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.houry.netty.barrage.proto.BarrageProto;

import java.net.URI;
import java.util.Random;

/**
 * WSocket 客户端
 *
 * @author chenqian09
 */
public class NettyClientClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientClient.class);
    private static String serverIp = "10.252.72.159";
    private static int serverPort = 9999;
//    ws://twinklejxedttest.58v5.cn:80/ws

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new WebSocketClientInit());
        ChannelFuture future;
        try {
            URI websocketURI = new URI(String.format("ws://%s:%d/ws", serverIp, serverPort));
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, httpHeaders);
            Channel channel = bootstrap.connect(websocketURI.getHost(), websocketURI.getPort()).sync().channel();
            WebSocketClientHandler handler = (WebSocketClientHandler) channel.pipeline().get("client_handler");
            handler.setHandshaker(handshaker);
            handshaker.handshake(channel);
            future = handler.handshakeFuture().sync();

            while (true) {
                channel.writeAndFlush(hearBeat()).addListener(future1 -> {
                    if(future1.isSuccess()){
                        System.out.println("111");
                    }else{
                        future1.cause().printStackTrace();
                    }
                });
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    public static BarrageProto.Barrage hearBeat() {
        BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
        builder.setMsgType("heartbeat");
        return builder.build();
    }
}