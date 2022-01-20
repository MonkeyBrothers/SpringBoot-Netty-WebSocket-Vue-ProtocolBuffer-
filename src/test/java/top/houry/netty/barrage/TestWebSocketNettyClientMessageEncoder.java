package top.houry.netty.barrage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @Desc
 * @Author houruiyang
 * @Date 2022/1/20
 **/

public class TestWebSocketNettyClientMessageEncoder extends MessageToMessageEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        WebSocketFrame frame = new BinaryWebSocketFrame(msg);
        out.add(frame);
        msg.retain();
    }
}
