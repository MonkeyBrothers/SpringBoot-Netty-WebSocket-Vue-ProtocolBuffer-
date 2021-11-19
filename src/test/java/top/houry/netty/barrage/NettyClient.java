package top.houry.netty.barrage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.houry.netty.barrage.proto.BarrageProto;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2021/11/19
 **/
public class NettyClient {


    public static void main(String[] args) {
        Bootstrap client = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();

    }
}
