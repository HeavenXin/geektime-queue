package netty.test.talke.li.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.util.Strings;

import java.util.HashMap;
import java.util.Map;


public class LiMsgHandler extends SimpleChannelInboundHandler<String> {
    private static Map<String, String> msgs;


    static {
        msgs = new HashMap<>();
        msgs.put("吃了没?","刚吃");
        msgs.put("您这,嘛去","嗨!吃饱了溜溜弯儿");
        msgs.put("有空家里坐坐啊","回头去给老太太请安");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        String returnMsg = msgs.get(s);
        if (Strings.isNotEmpty(returnMsg)){
            System.out.println(returnMsg);
            channelHandlerContext.writeAndFlush(returnMsg);
        }
    }
}
