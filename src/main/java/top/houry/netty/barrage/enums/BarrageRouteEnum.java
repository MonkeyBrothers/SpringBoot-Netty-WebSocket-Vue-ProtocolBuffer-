package top.houry.netty.barrage.enums;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.Getter;
import top.houry.netty.barrage.service.BarrageService;
import top.houry.netty.barrage.utils.SpringContextUtil;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Desc 弹幕路由枚举类
 * @Author houruiyang
 * @Date 2021/8/16
 **/
public enum BarrageRouteEnum {

    HEART_BEAT_SERVICE("heart_beat", "heartBeatBarrageServiceImpl"),
    BARRAGE_MESSAGE("barrage_msg", "dealWithBarrageServiceImpl"),





    ;


    public BarrageService getService() {
        return (BarrageService) Optional.of(SpringContextUtil.getBean(this.getBeanName())).orElse(null);
    }

    /**
     * 根据msgType查询对应的枚举类型
     *
     * @param msgType 消息类型
     * @return 枚举类型
     */
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
