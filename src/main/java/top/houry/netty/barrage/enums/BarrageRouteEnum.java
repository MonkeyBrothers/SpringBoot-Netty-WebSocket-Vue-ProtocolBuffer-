package top.houry.netty.barrage.enums;

import lombok.Data;
import lombok.Getter;
import top.houry.netty.barrage.service.BarrageService;
import top.houry.netty.barrage.utils.SpringContextUtil;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2021/8/16
 **/
public enum BarrageRouteEnum {

    HEART_BEAT_SERVICE("heart_beat", "HeartBeatServiceImpl")

    ;




    public BarrageService getService() {
        return (BarrageService) Optional.of(SpringContextUtil.getBean(this.getBeanName())).orElse(null);
    }


    public static BarrageRouteEnum findByType(String msgType) {
        return Arrays.stream(BarrageRouteEnum.values()).filter(barrageRouteEnum -> barrageRouteEnum.msgType.equals(msgType)).findFirst().orElse(null);
    }


    BarrageRouteEnum(String msgType, String beanName) {
        this.msgType = msgType;
        this.beanName = beanName;
    }

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 对应的bean名称
     */
    @Getter
    private String beanName;
}
