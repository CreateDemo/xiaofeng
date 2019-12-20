package com.example.feng.netty.init;

import com.example.feng.netty.handler.HttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

import java.awt.*;

/**
 * @author admin
 * @create 2019-12-19-14:50
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //前面的名字（如httpServerCodeC）是可选的，如果没有会自动生成一个
        pipeline.addLast("httpServerCodeC",new HttpServerCodec());
        pipeline.addLast("HttpServerHandler",new HttpServerHandler());

    }
}
