package com.example.nettydemo.netty;

/**
 * @Author: wsh
 * @Date: 2019/9/6 14:39
 * @Description: *
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    /*
    读取客户端写过来的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server : " + ctx);
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发来的消息 ：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /*
        读完客户端之后，给客户端的响应操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("就是没钱"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()), CharsetUtil.UTF_8));
    }
}