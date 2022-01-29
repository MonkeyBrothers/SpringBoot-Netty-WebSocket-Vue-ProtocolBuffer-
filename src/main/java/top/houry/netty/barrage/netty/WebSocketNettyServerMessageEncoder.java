package top.houry.netty.barrage.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/18
 **/
public class WebSocketNettyServerMessageEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        WebSocketFrame frame = new BinaryWebSocketFrame(msg);
        out.add(frame);
        frame.retain();
    }
}
