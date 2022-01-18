package top.houry.netty.barrage.common;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/18
 **/
public interface BarrageWebSocketConst {

    String WEB_SOCKET_PATH = "/ws";

    int MAX_CONTENT_LENGTH = 2048;

    int READER_IDLE_TIME = 10;

    int WRITE_IDLE_TIME = 0;

    int ALL_IDLE_TIM = 0;
}
