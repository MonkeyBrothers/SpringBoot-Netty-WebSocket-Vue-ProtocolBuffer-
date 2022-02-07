package top.houry.netty.barrage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import top.houry.netty.barrage.entity.BarrageMsg;
import top.houry.netty.barrage.dao.BarrageMsgMapper;
import top.houry.netty.barrage.service.IBarrageMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<BarrageMsg> getListByVideoId(String videoId) {
        return list(Wrappers.<BarrageMsg>lambdaQuery()
                .eq(BarrageMsg::getDelFlag, false)
                .eq(BarrageMsg::getVideoId, videoId)
        );
    }
}
