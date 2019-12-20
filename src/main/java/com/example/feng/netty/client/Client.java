package com.example.feng.netty.client;

import com.example.feng.netty.init.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author admin
 * @create 2019-12-19-16:50
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup =new NioEventLoopGroup();
        try {
            Bootstrap bootstrap =new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientInitializer());
            ChannelFuture sync = bootstrap.connect("localhost",8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
