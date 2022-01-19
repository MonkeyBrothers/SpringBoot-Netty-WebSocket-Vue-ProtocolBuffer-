package top.houry.netty.barrage.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Desc
 * @Author houry
 * @Date 2021/5/12 13:57
 **/
@Component
public class BarrageSpringContextUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BarrageSpringContextUtil.applicationContext = applicationContext;
    }


    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }


    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return  applicationContext.getBean(clazz);
    }


}