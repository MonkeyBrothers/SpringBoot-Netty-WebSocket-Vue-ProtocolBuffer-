package top.houry.netty.barrage.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Desc 弹幕连接信息
 * @Author houruiyang
 * @Date 2022/1/22
 **/
public class BarrageConnectInfoUtils {

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static final Lock READ_LOCK = READ_WRITE_LOCK.readLock();
    private static final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    /**
     * 维护了视频ID和目前观看视频的用户的通道
     */
    public static final Map<String, List<ChannelHandlerContext>> BASE_CONNECT_INFO_MAP = new ConcurrentHashMap<>(256);

    public static final Map<ChannelHandlerContext, String> BASE_CHANNEL_VIDEO_MAP = new ConcurrentHashMap<>(256);

    /**
     * 根据视频ID获取对应的用户连接通道信息
     *
     * @param videoId 视频ID
     * @return 通道信息集合
     */
    public static List<ChannelHandlerContext> getChannelHandlerContextListByVideId(String videoId) {
        if (StringUtils.isBlank(videoId)) return null;
        List<ChannelHandlerContext> channelHandlerContexts = BASE_CONNECT_INFO_MAP.get(videoId);
        if (CollectionUtils.isEmpty(channelHandlerContexts)) return null;
        return channelHandlerContexts;
    }

    /**
     * 添加视频对应的通道信息到缓存中
     *
     * @param videoId 视频ID
     * @param context channel 上下文
     * @return 添加结果
     */
    public static boolean addChannelInfoToBaseMap(String videoId, ChannelHandlerContext context) {
        if (StringUtils.isBlank(videoId) || null == context || !context.channel().isActive()) return false;
        List<ChannelHandlerContext> channelHandlerContexts = BASE_CONNECT_INFO_MAP.get(videoId);
        if (CollectionUtils.isEmpty(channelHandlerContexts)) {
            BASE_CONNECT_INFO_MAP.put(videoId, Collections.singletonList(context));
            return true;
        }
        try {
            WRITE_LOCK.lock();
            BASE_CHANNEL_VIDEO_MAP.put(context, videoId);
            return channelHandlerContexts.add(context);
        } catch (Exception e) {
            return false;
        } finally {
            WRITE_LOCK.unlock();
        }

    }

    /**
     * 从缓存中移除掉用户的基础连接信息
     *
     * @param videoId 视频ID
     * @param context 通道信息
     * @return 移除结果
     */
    public static boolean removeChannelInfoFromBaseMap(String videoId, ChannelHandlerContext context) {
        if (StringUtils.isBlank(videoId) || null == context || !context.channel().isActive()) return false;
        List<ChannelHandlerContext> channelHandlerContexts = BASE_CONNECT_INFO_MAP.get(videoId);
        if (CollectionUtils.isEmpty(channelHandlerContexts)) return true;
        if (!channelHandlerContexts.contains(context)) return true;
        try {
            WRITE_LOCK.lock();
            return channelHandlerContexts.remove(context);
        } catch (Exception e) {
            return false;
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    /**
     * 根据ChannelHandlerContext移除缓存中的信息
     *
     * @param context ChannelHandlerContext
     * @return 移除结果
     */
    public static boolean removeChannelInfoByChannelHandlerContext(ChannelHandlerContext context) {
        String videId = BASE_CHANNEL_VIDEO_MAP.get(context);


        return false;
    }





}
