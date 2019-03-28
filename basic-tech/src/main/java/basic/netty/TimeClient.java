package basic.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/3/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TimeClient {
    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new TimeClientInitializer());
            Channel f = b.connect(host, port).sync().channel();
//            f.channel().closeFuture().sync();
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String line=in.readLine();
                if (line==null){
                    continue;
                }
            f.writeAndFlush(line+"\r\n");
            }
        } finally {

            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 5688;
        try {
            TimeClient timeClient = new TimeClient();
            timeClient.connect(port, "127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
