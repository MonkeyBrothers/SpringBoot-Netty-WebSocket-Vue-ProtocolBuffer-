package top.houry.netty.barrage.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/18
 **/
public class WebSocketNettyServerMessageDecoder extends MessageToMessageDecoder<WebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf buf = msg.content();
        out.add(buf);
        buf.retain();
    }
}
