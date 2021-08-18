package top.houry.netty.barrage.vo;

import lombok.Data;

@Data
public class BarrageVo {

    /**
     * 发送的消息内容
     */
    private String msgType;

    /**
     * 发送的文本内容
     */
    private String context;
}
