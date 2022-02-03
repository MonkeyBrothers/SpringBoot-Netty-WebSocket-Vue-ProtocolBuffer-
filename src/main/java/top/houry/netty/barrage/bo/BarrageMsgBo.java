package top.houry.netty.barrage.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/2/2
 **/
@Data
public class BarrageMsgBo {

    private String msg;

    private String msgColor;

    private Integer msgPosition;


    private String userId;

    private String videId;

    public BarrageMsgBo() {
    }

    public BarrageMsgBo(String msg, String msgColor, Integer msgPosition, String userId, String videId) {
        this.msg = msg;
        this.msgColor = msgColor;
        this.msgPosition = msgPosition;
        this.userId = userId;
        this.videId = videId;
    }
}
