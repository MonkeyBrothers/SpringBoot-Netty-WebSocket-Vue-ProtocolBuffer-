package top.houry.netty.barrage.utils;

import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.service.IBarrageMsgTypeService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc 维护了自定义的消息注解名称和所对应的bean名称的工具类
 * @Author houruiyang
 * @Date 2021/11/19
 **/
public class BarrageMsgBeanUtils {

    /**
     * 维护了自定义的消息注解名称和所对应的bean名称
     */
    private static final Map<String, String> BARRAGE_MSG_BEAN = new ConcurrentHashMap<>(16);

    /**
     * 添加一个消息类型到容器中
     *
     * @param msgType  自定义的注解的消息类型{@link BarrageAnnotation#msgType}
     * @param beanName 对应的bean名字
     */
    public static void addMsgType(String msgType, String beanName) {
        BARRAGE_MSG_BEAN.put(msgType, beanName);
    }

    /**
     * 判断对应的消息类型是否存在
     *
     * @param msgType 自定义的注解的消息类型{@link BarrageAnnotation#msgType}
     * @return true-存在  false-不存在
     */
    public static boolean exist(String msgType) {
        return BARRAGE_MSG_BEAN.containsKey(msgType);
    }

    /**
     * 根据指定的消息体获取对应的bean
     *
     * @param msgType 自定义的注解的消息类型{@link BarrageAnnotation#msgType}
     * @return 对应的bean
     */
    public static IBarrageMsgTypeService getService(String msgType) {
        return BarrageSpringContextUtil.getBean(BARRAGE_MSG_BEAN.get(msgType));
    }

}
