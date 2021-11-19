package top.houry.netty.barrage.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import top.houry.netty.barrage.annotation.BarrageAnnotation;
import top.houry.netty.barrage.utils.BarrageMsgBeanUtils;


@Component
public class BarrageAnnotationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BarrageAnnotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), BarrageAnnotation.class);
        if (null == annotation) return bean;
        String msgType = annotation.msgType();
        if (BarrageMsgBeanUtils.exist(msgType)){
           throw new RuntimeException("不能存在相同的自定义消息注解:" + msgType);
        } else {
            BarrageMsgBeanUtils.addMsgType(msgType, beanName);
        }
        return bean;
    }
}
