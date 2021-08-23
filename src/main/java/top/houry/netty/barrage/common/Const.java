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
    }
}
