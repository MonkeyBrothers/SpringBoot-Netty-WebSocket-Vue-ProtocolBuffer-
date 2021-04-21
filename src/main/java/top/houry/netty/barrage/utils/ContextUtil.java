package top.houry.netty.barrage.utils;

import java.util.Random;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/8 16:11
 **/
public class ContextUtil {
    static String[] context = {"有人吗？","牛逼！！！","2333333","66666666","有点厉害了","卧槽！","撒花","flag","真牛逼","我来自中国","set down","in search of","to do something","I'm not used to being spoken to in that rude way.","The boy has become accustomed to speaking English in public.","I amvery much opposed to your going abroad.","He devoted himself to promoting (the promotion of) world peace.","The doctor dedicated himself to finding a cure for the disease.",};



    public static String getContext(){
        Random random = new Random();
        int i = random.nextInt(context.length);
        return context[i];
    }

}
