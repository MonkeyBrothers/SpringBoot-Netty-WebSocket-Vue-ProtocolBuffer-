package top.houry.netty.barrage.consts;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/29 11:36
 **/
public interface BarrageRedisKeyConst {

    /**
     * 弹幕在redis中存放的key
     */
    String BARRAGE_REDIS_LIST_KEY = "BARRAGE:LIST:";
    /**
     * 实时在线人数统计key
     */
    String BARRAGE_ONLINE_POPULATION_KEY = "BARRAGE:ONLINE:POPULATION:";
    /**
     * 历史观看总次数
     */
    String BARRAGE_TOTAL_WATCH_KEY = "BARRAGE:TOTAL:WATCH:";

    /**
     * 历史总弹幕数
     */
    String BARRAGE_TOTAL_MSG_KEY = "BARRAGE:TOTAL:MSG:";

}
