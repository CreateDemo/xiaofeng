package com.example.feng.netty.init;

import com.example.feng.netty.handler.MyHeartbeatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @create 2019-12-19-18:17
 */
public class MyHeartbeatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline =socketChannel.pipeline();
        channelPipeline.addLast(
                new IdleStateHandler(5,7,10, TimeUnit.SECONDS));
        channelPipeline.addLast(new MyHeartbeatServerHandler());
    }
}
