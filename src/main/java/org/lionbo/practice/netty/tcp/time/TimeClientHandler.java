package org.lionbo.practice.netty.tcp.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel active" + ctx.hashCode());
        String info = "lion2" + System.getProperty("line.separator");
        for (int i = 0; i < 100; i++) {
            final ByteBuf infoByte = ctx.alloc().buffer(info.getBytes().length);
            infoByte.writeBytes(info.getBytes());
            ctx.writeAndFlush(infoByte);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        byte[] req = new byte[m.readableBytes()];
        m.readBytes(req);
        String get = new String(req, "UTF-8");
        System.out.println(get + ":" + count++);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
