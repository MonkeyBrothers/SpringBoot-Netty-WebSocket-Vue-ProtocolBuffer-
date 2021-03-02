package top.houry.netty.bulletscreen.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.houry.netty.bulletscreen.config.NettyConfigProperties;

/**
 * @Desc netty 服务
 * @Author houry
 * @Date 2021/3/2 8:48
 **/
@Component
@Slf4j
public class NettyServer implements ApplicationRunner {

    @Autowired
    private NettyConfigProperties nettyConfigProperties;

    @Override
    public void run(ApplicationArguments args) {
        startNettyServer();
    }

    /**
     * 启动netty服务
     */
    private void startNettyServer() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(4);
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new NettyServerInitializer());
            ChannelFuture channelFuture = server.bind(nettyConfigProperties.getServerPort()).sync();
            log.info("[NettyServer]-[startNettyServer]-[start]");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("[NettyServer]-[startNettyServer]-[Exception]", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            log.info("[NettyServer]-[startNettyServer]-[shutdownGracefully]");
        }
    }
}
