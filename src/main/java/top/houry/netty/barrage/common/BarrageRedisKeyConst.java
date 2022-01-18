package top.houry.netty.barrage.common;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/29 11:36
 **/
public interface BarrageRedisKeyConst {

    /**
     * 弹幕在redis中存放的key
     */
    String BARRAGE_REDIS_LIST_KEY = "BARRAGE:LIST:KEY";
    /**
     * 实时在线人数统计key
     */
    String BARRAGE_ONLINE_POPULATION_KEY = "BARRAGE:ONLINE:POPULATION:KEY";
    /**
     * 历史观看总次数
     */
    String BARRAGE_TOTAL_NUMBER_KEY = "BARRAGE:TOTAL:NUMBER:KEY";

}
