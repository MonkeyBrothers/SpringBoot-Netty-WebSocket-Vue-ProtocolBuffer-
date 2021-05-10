package top.houry.netty.barrage.utils;

import java.util.Random;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/8 16:11
 **/
public class ContextUtil {
    static String[] context = {
            "最美的人儿",
            "我们都是追梦人！",
            "做的不错",
            "666666666",
            "这波操作不亏",
            "哈哈哈哈",
            "OK",
            "太棒了",
            "学习一下",

    };



    public static String getContext(){
        Random random = new Random();
        int i = random.nextInt(context.length);
        return context[i];
    }

}
