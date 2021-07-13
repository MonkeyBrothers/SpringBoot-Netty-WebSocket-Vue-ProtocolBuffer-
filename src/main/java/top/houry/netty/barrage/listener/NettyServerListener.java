package top.houry.netty.barrage.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc NettyServer绑定端口的监听事件
 * @Author houry
 * @Date 2021/4/21 15:57
 **/
@Slf4j
public class NettyServerListener implements ChannelFutureListener {

    /**
     * 端口号
     */
    private final Integer port;

    public NettyServerListener(Integer port) {
        this.port = port;
    }

    /**
     * 端口绑定之后发生的操作
     *
     * @param future
     * @throws Exception
     */
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            log.info("[NettyServerListener]-[NettyServer bind port {} success]", port);
        } else {
            log.info("[NettyServerListener]-[NettyServer bind port {} fail]", port);
        }
    }
}
