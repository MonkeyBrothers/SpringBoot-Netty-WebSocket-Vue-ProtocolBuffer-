package top.houry.netty.barrage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.houry.netty.barrage.netty.WebSocketNettyServer;
/**
 * @Desc 抽取公共的需要在SpringBoot加载完毕之后加载的业务
 * @Author houry
 * @Date 2021/3/2 8:48
 **/
@Component
public class BarrageRunner implements ApplicationRunner {

    @Autowired
    private WebSocketNettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(() ->{
            // 后期如果有其他的业务需要启动，注入进拿来在这里启动即可，需要注意的是非阻塞线程的业务。
            nettyServer.startNettyServer();

        }).start();
    }
}
