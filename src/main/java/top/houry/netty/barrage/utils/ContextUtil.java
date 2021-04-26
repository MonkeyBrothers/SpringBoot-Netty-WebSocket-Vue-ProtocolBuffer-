package top.houry.netty.barrage.utils;

import java.util.Random;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/8 16:11
 **/
public class ContextUtil {
    static String[] context = {
            "扫描下方二维码，扫不出吃亏，扫不出上当！",

    };



    public static String getContext(){
        Random random = new Random();
        int i = random.nextInt(context.length);
        return context[i];
    }

}
