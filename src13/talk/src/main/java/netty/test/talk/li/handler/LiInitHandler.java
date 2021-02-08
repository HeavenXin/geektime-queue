package netty.test.talke.li.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import netty.test.talke.common.handler.MsgEncoder;

import java.nio.charset.StandardCharsets;


public class LiInitHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new LengthFieldBasedFrameDecoder(1024,0,4))
                .addLast(new StringDecoder(StandardCharsets.UTF_8))
                .addLast(new MsgEncoder())
                .addLast(new LiMsgHandler());
    }
}
