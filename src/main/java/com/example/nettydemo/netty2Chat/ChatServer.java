package com.example.nettydemo.netty2Chat;

/**
 * @Author: wsh
 * @Date: 2019/9/6 15:03
 * @Description: *
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 聊天程序服务器端
 */
public class ChatServer {

    /**
     * 服务器端端口号
     */
    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //1.创建一个线程组，专门用来接收客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2.创建一个线程组，专门用来网络读写操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //3.服务端启动的助手，进行服务端的一系列设置
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    // 往pipeline链中添加一个解码器
                                    .addLast("decoder", new StringDecoder())
                                    // 往pipeline链中添加一个编码器
                                    .addLast("encoder", new StringEncoder())
                                    // 往pipline链中添加自定义的handler（业务处理类）
                                    .addLast(new ChatServerHandler());
                        }
                    });
            System.out.println("Netty chat Server启动。。。");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("Netty chat Server关闭。。。");
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatServer(9999).run();
    }

}
