package top.houry.netty.barrage.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.utils.BarrageMsgBeanUtils;

/**
 * @Desc 自定义消息注解处理类
 * @Author houruiyang
 * @Date 2021/11/27
 **/
@Component
@Slf4j
public class BarrageAnnotationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BarrageAnnotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), BarrageAnnotation.class);
        if (null == annotation) return bean;
        String msgType = annotation.msgType();
        if (BarrageMsgBeanUtils.exist(msgType)) throw new RuntimeException(msgType + "is already exist");
        BarrageMsgBeanUtils.addMsgType(msgType, beanName);
        log.info("[BarrageAnnotationPostProcessor]-[postProcessAfterInitialization]-[添加消息类型到缓存中]-[msgType:{}]", msgType);
        return bean;
    }
}
