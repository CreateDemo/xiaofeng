package com.example.feng.netty.server;

import com.example.feng.netty.init.MyHeartbeatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author admin
 * @create 2019-12-20-19:08
 */
public class MyWebSocketServer {
    public static void main(String[] args) {
        //boss 负责连接  负责和客户端建立连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker boss进行连接后 将工作交给 worker来做
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(null);
            //建立绑定端口
            ChannelFuture sync = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
