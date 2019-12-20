package com.example.feng.netty.init;

import com.example.feng.netty.handler.MyChatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.channels.Pipe;
import java.nio.charset.Charset;


/**
 * @author admin
 * @create 2019-12-19-17:15
 */
public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline =socketChannel.pipeline();
        channelPipeline.addLast(new DelimiterBasedFrameDecoder(4096,
                Delimiters.lineDelimiter()));
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new MyChatServerHandler());

    }
}
