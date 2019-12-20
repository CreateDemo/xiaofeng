package com.example.feng.netty.server;

import com.example.feng.netty.init.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author admin
 * @create 2019-12-19-14:38
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {

        //boss 负责连接  负责和客户端建立连接
        EventLoopGroup  bossGroup = new NioEventLoopGroup();
        //worker boss进行连接后 将工作交给 worker来做
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ServerInitializer());
            //建立绑定端口
            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }




    }
}
