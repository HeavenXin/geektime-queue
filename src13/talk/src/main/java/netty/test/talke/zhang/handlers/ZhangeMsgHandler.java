package netty.test.talke.zhang.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.util.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author XinLiu
 * @version 1.0
 * <p> xinliucl@isoftstone.com </p >
 */
public class ZhangeMsgHandler extends SimpleChannelInboundHandler<String> {

    private static final int limit = 100 * 10000;

    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

    private static Map<String, String> msgs;

    private static final String initTalk = "吃了没?";

    static {
        msgs = new HashMap<>();
        msgs.put("刚吃", "您这,嘛去");
        msgs.put("嗨!吃饱了溜溜弯儿", "有空家里坐坐啊");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        singleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < limit; i++) {
                    ctx.writeAndFlush(initTalk);
                }
            }
        });
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        String returnMsg = msgs.get(s);
        if (Strings.isNotEmpty(returnMsg)){
            System.out.println(returnMsg);
            channelHandlerContext.writeAndFlush(returnMsg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
