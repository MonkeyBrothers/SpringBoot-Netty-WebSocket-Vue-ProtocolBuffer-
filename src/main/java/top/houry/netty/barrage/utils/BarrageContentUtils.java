package top.houry.netty.barrage.utils;

import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import top.houry.netty.barrage.bo.BarrageMsgBo;
import top.houry.netty.barrage.consts.BarrageRedisKeyConst;
import top.houry.netty.barrage.consts.BarrageVideoConst;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/8 16:11
 **/
public class BarrageContentUtils {

    public static BarrageMsgBo getContext(List<BarrageMsgBo> msgBos) {
        Random random = new Random();
        int i = random.nextInt(msgBos.size());
        return msgBos.get(i);
    }

    public static BarrageMsgBo getContext() {
        BarrageRedisUtils redisUtils = BarrageSpringContextUtil.getBean(BarrageRedisUtils.class);
        List<String> barrages = redisUtils.listGetAll(BarrageRedisKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.videId);
        List<BarrageMsgBo> barrageMsgList = new ArrayList<>(barrages.size());
        barrages.forEach(v -> {
            BarrageMsgBo msgBo = JSONUtil.toBean(v, BarrageMsgBo.class);
            barrageMsgList.add(msgBo);
        });
        return getContext(barrageMsgList);
    }

//    public static String getContext() {
//        BarrageRedisUtils redisUtils = BarrageSpringContextUtil.getBean(BarrageRedisUtils.class);
//        List<String> barrages = redisUtils.listGetAll(BarrageRedisKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.videId);
//        List<String> barrageMsgList = new ArrayList<>(barrages.size());
//        barrages.forEach(v -> {
//            BarrageMsgBo msgBo = JSONUtil.toBean(v, BarrageMsgBo.class);
//            barrageMsgList.add(StringUtils.isBlank(msgBo.getMsg()) ? "" : msgBo.getMsg());
//        });
//        return getContext(barrageMsgList.toArray(new String[0]));
//    }

}
