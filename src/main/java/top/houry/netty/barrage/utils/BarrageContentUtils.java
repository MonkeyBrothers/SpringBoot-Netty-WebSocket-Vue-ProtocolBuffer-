package top.houry.netty.barrage.utils;

import java.util.List;
import java.util.Random;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/8 16:11
 **/
public class BarrageContentUtils {
    public static String[] context = {
            "最美的人儿",
            "我们都是追梦人！",
            "做的不错",
            "666666666",
            "这波操作不亏",
            "哈哈哈哈",
            "OK",
            "太棒了",
            "学习一下",
            "欢迎扫码关注",
            "微信搜索:小猴子的技术笔记",
            "项目地址:https://gitee.com/MonkeyBrothers/barrage"

    };



    public static String getContext(List<String> barrage){
        Random random = new Random();
        int i = random.nextInt(barrage.size());
        return barrage.get(i);
    }

}
