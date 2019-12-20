package com.example.feng.netty.init;

import com.example.feng.netty.handler.HttpClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author admin
 * @create 2019-12-19-16:54
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("httpServerCodeC",new HttpServerCodec());
        pipeline.addLast("httpClientHandler",new HttpClientHandler());

    }
}
