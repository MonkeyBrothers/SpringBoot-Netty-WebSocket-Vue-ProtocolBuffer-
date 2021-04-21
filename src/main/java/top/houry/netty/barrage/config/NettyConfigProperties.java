package top.houry.netty.barrage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc 读取配置文件信息
 * @Author houry
 * @Date 2021/3/2 9:40
 **/
@Configuration
@ConfigurationProperties(prefix = "netty")
public class NettyConfigProperties {

    /**
     * netty 服务端监听端口
     */
    @Getter
    @Setter
    private Integer serverPort;
}
