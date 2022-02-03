package top.houry.netty.barrage.utils;

import java.util.Random;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/8 16:11
 **/
public class BarrageContentUtils {
    public static String[] context = {
            "我们都是追梦人！",
            "做的不错",
            "666666666",
            "哈哈哈哈",
            "新年好啊",
            "OK",
            "太棒了",
            "学习一下",
            "版本在持续不断的更迭中",
            "欢迎大家踊跃的加入啊",
            "微信搜索:小猴子的技术笔记",
            "小猴子祝您，虎年大吉，虎虎生威",
    };

    public static String getContext(String[] barrage){
        Random random = new Random();
        int i = random.nextInt(barrage.length);
        return barrage[i];
    }

    public static String getContext() {
        return getContext(context);
    }

}
