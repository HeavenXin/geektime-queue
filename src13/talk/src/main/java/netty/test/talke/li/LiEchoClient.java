package netty.test.talke.li;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.test.talke.li.handler.LiInitHandler;

/**
 * @author XinLiu
 * @version 1.0
 * <p> xinliucl@isoftstone.com </p >
 */
public class LiEchoClient {

    private static final String address = "127.0.0.1";

    private final int port;

    public LiEchoClient(int port) {
        this.port = port;
    }

    public void start() {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new LiInitHandler());
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000);
        ChannelFuture chF = bootstrap.connect(address, port);
        chF.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("看见李大爷了!");
                } else {
                    System.out.println("李大爷不理人!");
                }
            }
        });
    }
}
