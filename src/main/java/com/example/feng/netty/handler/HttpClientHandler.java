package com.example.feng.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;

/**
 * @author admin
 * @create 2019-12-19-16:56
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress());
        System.out.println("===client===");
        channelHandlerContext.writeAndFlush("client  hello world!");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       ctx.writeAndFlush("来自客户端的问候！");

    }
}
