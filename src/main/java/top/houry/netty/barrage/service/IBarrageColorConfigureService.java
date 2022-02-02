package top.houry.netty.barrage.service;

import top.houry.netty.barrage.entity.BarrageColorConfigure;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 弹幕颜色配置表 服务类
 * </p>
 *
 * @author houry
 * @since 2022-02-02
 */
public interface IBarrageColorConfigureService extends IService<BarrageColorConfigure> {
    List<BarrageColorConfigure> getAll();
}
