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
@NoArgsConstructor
@AllArgsConstructor
public class BarrageMsgBo {

    private String msg;

    private String msgColor;

    private String userId;

    private String videId;
}
