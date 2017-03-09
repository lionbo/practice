package org.lionbo.practice.netty.tcp.time;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("server channel active" + ctx.hashCode());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        byte[] readableBytes = new byte[m.readableBytes()];
        m.readBytes(readableBytes);
        String info = new String(readableBytes, "UTF-8").substring(0,
                readableBytes.length - System.getProperty("line.separator").length());
        System.out.println(info + ":" + count++);

        String currentTime = "callback" + new Date() + System.getProperty("line.separator");
        ByteBuf callback = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(callback);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
