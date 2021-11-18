package top.houry.netty.barrage.annotation;


import java.lang.annotation.*;


/**
 * 自定义枚举注解，用来标明弹幕的消息体类别
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface BarrageAnnotation {
    String msgType() default "";
}
