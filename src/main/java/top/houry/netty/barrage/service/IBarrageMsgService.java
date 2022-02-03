package top.houry.netty.barrage.service;

import top.houry.netty.barrage.entity.BarrageMsg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author houry
 * @since 2022-02-03
 */
public interface IBarrageMsgService extends IService<BarrageMsg> {
    boolean saveBarrageMsg(BarrageMsg barrageMsg);
}
