package top.houry.netty.barrage.service.impl;

import top.houry.netty.barrage.entity.BarrageMsg;
import top.houry.netty.barrage.dao.BarrageMsgMapper;
import top.houry.netty.barrage.service.IBarrageMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author houry
 * @since 2022-02-03
 */
@Service
public class BarrageMsgServiceImpl extends ServiceImpl<BarrageMsgMapper, BarrageMsg> implements IBarrageMsgService {

    @Override
    public boolean saveBarrageMsg(BarrageMsg barrageMsg) {
        return save(barrageMsg);
    }
}
