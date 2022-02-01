package top.houry.netty.barrage.consts;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/30
 **/
public interface BarrageMsgTypeConst {
    /**
     * web客户端消息发送上行下行消息
     */
    String WEB_CLIENT_BARRAGE_REQ = "web.client.barrage.req";
    String WEB_CLIENT_BARRAGE_RESP = "web.client.barrage.resp";
    /**
     * web客户端心跳上行下行消息
     */
    String WEB_CLIENT_HEART_BEAT_REQ = "web.client.heartbeat.req";
    String WEB_CLIENT_HEART_BEAT_RESP = "web.client.heartbeat.resp";
    /**
     * web客户端登录上行下行消息
     */
    String WEB_CLIENT_LOGIN_REQ = "web.client.login.req";
    String WEB_CLIENT_LOGIN_RESP = "web.client.login.resp";
    /**
     * web客户端退出上行下行消息
     */
    String WEB_CLIENT_LOGOUT_REQ = "web.client.logout.req";
    String WEB_CLIENT_LOGOUT_RESP = "web.client.logout.resp";
}
