package basic.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/3/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class TimerClientHandler extends ChannelInboundHandlerAdapter {
    public ByteBuf getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(ByteBuf firstMessage) {
        this.firstMessage = firstMessage;
    }

    private ByteBuf firstMessage;

    public TimerClientHandler() {
        byte[] req="abc\r\n".getBytes();
        this.firstMessage= Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

//        super.channelActive(ctx);
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("now is "+body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
