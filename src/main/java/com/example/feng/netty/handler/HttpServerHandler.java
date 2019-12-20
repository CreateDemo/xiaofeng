package com.example.feng.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URL;


/**
 * @author admin
 * @create 2019-12-19-14:53
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {



    /**
     * 接收到消息执行的回调方法
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
           HttpRequest httpRequest = (HttpRequest) httpObject;
           //返回内容
           ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
           URI uri =new URI(httpRequest.uri());
           System.out.println("请求方式："+httpRequest.method().name());
           if("/favicon.ico".equals(uri.getPath())){
               System.out.println("favicon.ico");
               return;
           }
           //        //返回协议
           //HttpVersion.HTTP_1_1 ：HTTP协议 1.1
           //HttpResponseStatus.OK  ：返回状态
           FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                   HttpResponseStatus.OK,content);
           response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
           response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
           channelHandlerContext.writeAndFlush(response);



    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }
}
