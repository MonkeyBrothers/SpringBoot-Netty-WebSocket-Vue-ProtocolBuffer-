package top.houry.netty.barrage.scheduled;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.houry.netty.barrage.bo.BarrageMsgBo;
import top.houry.netty.barrage.service.IBarrageSendMsgToClientService;
import top.houry.netty.barrage.utils.BarrageConnectInfoUtils;
import top.houry.netty.barrage.utils.BarrageContentUtils;
import top.houry.netty.barrage.utils.BarrageRedisUtils;

import java.util.List;

/**
 * @Desc
 * @Author houry
 * @Date 2021/5/10 15:05
 **/
@Component
@Slf4j
public class BarrageScheduling {

    private BarrageRedisUtils redisUtils;

    private IBarrageSendMsgToClientService barrageSendMsgToClientService;

    @Autowired
    public void setBarrageSendMsgToClientService(IBarrageSendMsgToClientService barrageSendMsgToClientService) {
        this.barrageSendMsgToClientService = barrageSendMsgToClientService;
    }
    @Autowired
    public void setRedisUtils(BarrageRedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    @Scheduled(fixedRate = 3000)
    public void pushHistoryBarrage() {
        List<ChannelHandlerContext> contexts = BarrageConnectInfoUtils.getChannelHandlerContextListByVideId("1294384753222123");
        if (CollectionUtils.isEmpty(contexts)) return;
        BarrageMsgBo barrageMsgBo = new BarrageMsgBo(BarrageContentUtils.getContext(), "#fff", 0, null, null);
        contexts.forEach(v -> barrageSendMsgToClientService.sendMsg(barrageMsgBo, v));
    }

}
