package top.houry.netty.barrage.service;

public interface BarrageService {
    /**
     * 处理上送的弹幕信息
     *
     * @param text 弹幕信息
     */
    void dealWithBarrageMessage(String text);
}
