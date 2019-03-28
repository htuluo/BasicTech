package basic.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.sound.midi.Soundbank;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/3/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TimerClientSimpleHandler extends SimpleChannelInboundHandler<String> {

    private ByteBuf firstMessage;

    public TimerClientSimpleHandler() {
        byte[] req="abc\r\n".getBytes();
        this.firstMessage= Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//        channelHandlerContext.writeAndFlush("Server say:"+s);
        System.out.println("Server say:"+s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active");
//        super.channelActive(ctx);
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
