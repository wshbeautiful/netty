package com.example.nettydemo.netty;

/**
 * @Author: wsh
 * @Date: 2019/9/6 14:41
 * @Description: *
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端业务处理类
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 通道就绪事件
     * 像是监听服务端是否启动就绪
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client : " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("老板，还钱吧"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()), CharsetUtil.UTF_8));
    }

    /*
    读取服务端写回来的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务器端发来的消息 ： " + byteBuf.toString(CharsetUtil.UTF_8));
    }

}
