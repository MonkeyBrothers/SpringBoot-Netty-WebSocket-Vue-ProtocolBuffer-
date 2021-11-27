package top.houry.netty.barrage.netty;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;

/**
 * @Desc Ip过滤器自定义设置
 * @Author houruiyang
 * @Date 2021/11/27
 **/
public class NettyServerIpFilterHandler implements IpFilterRule {

    /**
     * 定义Ip匹配规则，这里只有返回true才会执行 {@link NettyServerIpFilterHandler#ruleType}
     *
     * @param inetSocketAddress InetSocketAddress
     * @return true-执行自定义策略 false-不执行自定义策略
     */
    @Override
    public boolean matches(InetSocketAddress inetSocketAddress) {
        return false;
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.REJECT;
    }
}
