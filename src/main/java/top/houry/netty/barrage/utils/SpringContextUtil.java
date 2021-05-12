package top.houry.netty.barrage.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Desc
 * @Author houry
 * @Date 2021/5/12 13:57
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext;


    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }


    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return  applicationContext.getBean(clazz);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}