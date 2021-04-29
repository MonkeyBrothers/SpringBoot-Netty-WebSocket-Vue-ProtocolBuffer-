package top.houry.netty.barrage.netty;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/29 10:22
 **/
public class NettyThreadFactory implements ThreadFactory {
    /**
     * 线程池数量
     */
    private final AtomicInteger poolNumber = new AtomicInteger(1);
    /**
     * 线程组
     */
    private final ThreadGroup threadGroup;
    /**
     * 线程数量
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 线程名前缀
     */
    public final String namePrefix;

    public NettyThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        if (null == name || "".equals(name.trim())) name = "pool";
        namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
