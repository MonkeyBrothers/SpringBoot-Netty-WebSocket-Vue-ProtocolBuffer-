package top.houry.netty.barrage.common;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/29 11:36
 **/
public interface Const {

    /**
     * websocket 上送的心跳信息
     */
    String WEBSOCKET_HEARTBEAT_INFO_FLAG = "WEBSOCKET_HEARTBEAT_INFO_FLAG";

    /**
     * 弹幕在redis中存放的key
     */
    String BARRAGE_REDIS_LIST_KEY = "BARRAGE_REDIS_LIST_KEY";
}
