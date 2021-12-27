package top.houry.netty.barrage;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import top.houry.netty.barrage.netty.NettyServerIpFilterHandler;
import top.houry.netty.barrage.proto.BarrageProto;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @author chenqian09
 */
public class WebSocketClientInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
//        pipeline
//                .addLast(new HttpClientCodec())
//                .addLast(new HttpObjectAggregator(65536))
//                .addLast("chunkedWriteHandler", new ChunkedWriteHandler())
//                .addLast(new ProtobufVarint32FrameDecoder())
//                .addLast("protobufDecoder", new ProtobufDecoder(BarrageProto.Barrage.getDefaultInstance()))
//                .addLast(new ProtobufVarint32LengthFieldPrepender())
//                .addLast("protobufEncoder", new ProtobufEncoder())
//                .addLast("client_handler", new WebSocketClientHandler())
//                .addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
//                    @Override
//                    protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
//                        ByteBuf result = null;
//                        if (msg instanceof MessageLite) {
//                            result = wrappedBuffer(((MessageLite) msg).toByteArray());
//                        }
//                        if (msg instanceof MessageLite.Builder) {
//                            result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
//                        }
//                        WebSocketFrame frame = new BinaryWebSocketFrame(result);
//                        out.add(frame);
//                    }
//                });


      //  pipeline.addLast("nettyServerIpFilterHandler", new RuleBasedIpFilter(new NettyServerIpFilterHandler()));
//        pipeline.addLast("httpServerCodec", new HttpClientCodec());
//        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(65536));
//        pipeline.addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
//        pipeline.addLast("protobufDecoder", new ProtobufDecoder(BarrageProto.Barrage.getDefaultInstance()));
//        pipeline.addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());
//        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
//        pipeline.addLast("client_handler", new WebSocketClientHandler());

        pipeline.addLast("Http_client_codec",new HttpClientCodec());
        pipeline.addLast("http_object_aggregator",new HttpObjectAggregator(65536));
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast("protobuf_varint32_frame_decoder",new ProtobufVarint32FrameDecoder())
                .addLast("protobuf_varint32_length_field_prepender",new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(BarrageProto.Barrage.getDefaultInstance()));
        pipeline.addLast("client_handler", new WebSocketClientHandler());
    }
}