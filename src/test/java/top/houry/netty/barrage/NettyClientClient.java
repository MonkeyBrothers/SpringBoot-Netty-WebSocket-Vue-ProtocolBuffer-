package top.houry.netty.barrage;

import com.google.protobuf.ByteString;
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
import java.util.concurrent.TimeUnit;


public class NettyClientClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientClient.class);
    private static String serverIp = "localhost";
    private static int serverPort = 9999;

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

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            builder.setMsgType("hearBeat");
            builder.setBytesData(ByteString.copyFromUtf8("hello"));
           while (true) {
               TimeUnit.SECONDS.sleep(2);
               channel.writeAndFlush(builder).addListener(future1 -> {
                   if(future1.isSuccess()){
                       System.out.println("111");
                   }else{
                       future1.cause().printStackTrace();
                   }
               });

           }
//            for(int i=0; i<100; i++) {
//                 channel.writeAndFlush(getCourseInstructExecutionResultMessage());
////                channel.writeAndFlush(systemBreak());
//
//            }
//            channel.writeAndFlush(heart());
//            while (true) {
//                Thread.sleep(5000);
//                channel.writeAndFlush(getRobotMotionReqMessage()).addListener(future1 -> {
//                    if(future1.isSuccess()){
//                        System.out.println("111");
//                    }else{
//                        future1.cause().printStackTrace();
//                    }
//                });
//            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


}