package netty.test.talke;

import netty.test.talke.li.LiEchoClient;
import netty.test.talke.zhang.ZhangEchoServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalkApplication.class, args);
        //开始李和张的聊天
        new ZhangEchoServer(9999).start();
        new LiEchoClient(9999).start();
    }

}
