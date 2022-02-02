package top.houry.netty.barrage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 弹幕颜色配置表
 * </p>
 *
 * @author houry
 * @since 2022-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_barrage_color_configure")
public class BarrageColorConfigure implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 颜色值
     */
    private String color;

    /**
     * 颜色编码
     */
    private String code;

    /**
     * 删除标识 0-未删除 1-已删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
