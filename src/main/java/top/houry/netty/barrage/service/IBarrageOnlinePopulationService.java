package top.houry.netty.barrage.service;

/**
 * @Desc 实时在线人数接口
 * @Author houruiyang
 * @Date 2021/9/1
 **/
public interface IBarrageOnlinePopulationService {

    void incrOne(String videoId);

    void decrOne(String videoId);

    int getCountByVideoId(String videoId);
}
