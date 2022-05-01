package top.houry.netty.barrage.service;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/2/5
 **/
public interface IBarrageWatchInfoService {

    void addOnlineWatchCount(String videoId);

    void subOnlineWatchCount(String videoId);

    void addTotalWatchCount(String videoId);

    String getTotalWatchCount(String videoId);

    String getTotalOnlineWatchCount(String videoId);
}
