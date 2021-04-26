package top.houry.netty.barrage.listener;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc NettyServer绑定端口的监听事件
 * @Author houry
 * @Date 2021/4/21 15:57
 **/
@Slf4j
public class NettyServerListener implements GenericFutureListener {
    private Integer port;

    public NettyServerListener(Integer port) {
        this.port = port;
    }

    @Override
    public void operationComplete(Future future) throws Exception {
        if (future.isSuccess()) {
            log.info("~~~~~~~~~~~~NettyServer bind port {} success~~~~~~~~~~~~~~", port);
        } else {
            log.info("~~~~~~~~~~~~NettyServer bind port {} fail   ~~~~~~~~~~~~~~", port);
        }
    }
}
