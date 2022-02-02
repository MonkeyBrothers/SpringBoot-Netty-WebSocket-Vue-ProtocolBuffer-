package top.houry.netty.barrage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import top.houry.netty.barrage.entity.BarrageColorConfigure;
import top.houry.netty.barrage.dao.BarrageColorConfigureMapper;
import top.houry.netty.barrage.service.IBarrageColorConfigureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 弹幕颜色配置表 服务实现类
 * </p>
 *
 * @author houry
 * @since 2022-02-02
 */
@Service
public class BarrageColorConfigureServiceImpl extends ServiceImpl<BarrageColorConfigureMapper, BarrageColorConfigure> implements IBarrageColorConfigureService {

    @Override
    public List<BarrageColorConfigure> getAll() {
        return list(Wrappers.<BarrageColorConfigure>lambdaQuery().eq(BarrageColorConfigure::getDelFlag, false));
    }
}
