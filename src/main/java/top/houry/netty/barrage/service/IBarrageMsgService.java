package top.houry.netty.barrage.service;

import top.houry.netty.barrage.entity.BarrageMsg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<BarrageMsg> getListByVideoId(String videoId);

    int getMsgCountByVideoId(String videoId);
}
