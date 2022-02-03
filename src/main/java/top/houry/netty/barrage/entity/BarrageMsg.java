package top.houry.netty.barrage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author houry
 * @since 2022-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_barrage_msg")
public class BarrageMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息位置
     */
    private Integer msgPosition;

    /**
     * 消息颜色
     */
    private String msgColor;

    /**
     * 发送消息的视频时间
     */
    private String videoTime;

    /**
     * 删除标识 0-未删除 1-已删除
     */
    private Boolean delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
