package top.houry.netty.barrage.common;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/18
 **/
public interface BarrageWebSocketConst {
    /**
     * WebSocket请求路径
     */
    String WEB_SOCKET_PATH = "/ws";

    /**
     * 请求最大长度
     */
    int MAX_CONTENT_LENGTH = 2048;

    /**
     * 读超时时间
     */
    int READER_IDLE_TIME = 10;

    /**
     * 写超时时间
     */
    int WRITE_IDLE_TIME = 0;

    /**
     * 读写超时时间
     */
    int ALL_IDLE_TIM = 0;
}
