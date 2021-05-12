package top.houry.netty.barrage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/21 16:17
 **/
@Data
@AllArgsConstructor
public class Barrage {


    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 弹幕发送时间
     */
    private Date sendTime;

    /**
     * 发送的消息
     */
    private String msg;


}
