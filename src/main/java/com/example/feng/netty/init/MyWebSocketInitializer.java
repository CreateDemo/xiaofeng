package com.example.feng.netty.init;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author admin
 * @create 2019-12-20-19:09
 */
public class MyWebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        //切分请求  HttpObjectAggregator 重要的处理器
        pipeline.addLast(new HttpObjectAggregator(8192));
        //ws://server:port/context_path
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(null);
    }
}
