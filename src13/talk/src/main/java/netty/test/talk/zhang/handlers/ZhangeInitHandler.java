package netty.test.talke.zhang.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import netty.test.talke.common.handler.MsgEncoder;

import java.nio.charset.StandardCharsets;



public class ZhangeInitHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new LengthFieldBasedFrameDecoder(1024*1024,0,4))
                .addLast(new StringDecoder(StandardCharsets.UTF_8))
                .addLast(new MsgEncoder())
                .addLast(new ZhangeMsgHandler());
    }
}
