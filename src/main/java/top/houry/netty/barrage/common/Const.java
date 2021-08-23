package top.houry.netty.barrage.common;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/29 11:36
 **/
public interface Const {

    class RedisKey {
        /**
         * 弹幕在redis中存放的key
         */
        public static final String BARRAGE_REDIS_LIST_KEY = "BARRAGE:LIST:KEY";
        /**
         * 实时在线人数统计key
         */
        public static final String REA_TIME_ONLINE_POPULATION_KEY = "REA:TIME:ONLINE:POPULATION:KEY";
        /**
         * 历史观看总次数
         */
        public static final String BARRAGE_TOTAL_NUMBER_KEY = "BARRAGE:TOTAL:NUMBER:KEY";
    }
}
