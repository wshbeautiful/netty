package com.example.nettydemo.netty2Chat;

/**
 * @Author: wsh
 * @Date: 2019/9/6 15:04
 * @Description: *
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 自定义一个客户端业务处理类
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s.trim());
    }
}
