package top.houry.netty.barrage;

import com.google.protobuf.ByteString;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import top.houry.netty.barrage.proto.BarrageProto;

import java.net.URI;
import java.util.concurrent.TimeUnit;


public class TestWebSocketNettyClient {


    public static void main(String[] args) {
        try {
            String uri = "ws://localhost:9999/ws";
            URI webSocketUri = new URI(uri);
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new TestWebSocketNettyClientInitializer());
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            WebSocketClientHandshaker handShaker = WebSocketClientHandshakerFactory.newHandshaker(webSocketUri, WebSocketVersion.V13, null, true, httpHeaders);
            Channel channel = bootstrap.connect(webSocketUri.getHost(), webSocketUri.getPort()).sync().channel();
            TestWebSocketNettyClientHandler handler = (TestWebSocketNettyClientHandler) channel.pipeline().get("testWebSocketNettyClientHandler");

            handler.setHandShaker(handShaker);
            handShaker.handshake(channel);

            handler.handshakeFuture().sync();

//            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
//            builder.setMsgType("server.heartBeat");
//            builder.setBytesData(ByteString.copyFromUtf8("hello"));



            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            builder.setMsgType("web.client.barrage");
            BarrageProto.WebClientSendBarrage.Builder login = BarrageProto.WebClientSendBarrage.newBuilder();
            login.setUserId("18429384029348532");
            login.setVideoId("74283940596332214");
            builder.setBytesData(login.build().toByteString());
            while (true) {

                channel.writeAndFlush(builder).addListener(future1 -> {
                    if (future1.isSuccess()) {
                        System.out.println("111");
                    } else {
                        future1.cause().printStackTrace();
                    }
                });
                TimeUnit.SECONDS.sleep(2);

            }

        } catch (Exception e) {

        }
    }


}