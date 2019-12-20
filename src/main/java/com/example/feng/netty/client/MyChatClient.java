package com.example.feng.netty.client;


import com.example.feng.netty.init.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author admin
 * @create 2019-12-19-17:40
 */
public class MyChatClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup =new NioEventLoopGroup();
        try {
            Bootstrap bootstrap =new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
            Channel channel = bootstrap.connect("localhost", 8899)
                    .sync().channel();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(bufferedReader.read()+"\r\n");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
