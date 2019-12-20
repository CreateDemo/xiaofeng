package com.example.feng.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;

/**
 * @author admin
 * @create 2019-12-19-17:20
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //channel组 保存同组的channel;
    private  static ChannelGroup channels =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(msg);
        channels.forEach(ch->{
            if(ch!=channel){
                ch.writeAndFlush(channel.remoteAddress()+"发送的消息:"+msg+"\n");
            }else {
                ch.writeAndFlush("【自己】"+"发送的消息:"+msg+"\n");
            }
        });
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"下线");
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if(channels.isEmpty()){
            channels.add(channel);
        }else {
            channels.add(channel);
            channels.writeAndFlush("【服务器】-"+channel.remoteAddress()+"加入了大家庭！");
        }

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("【服务器】-"+channel.remoteAddress()+"离开了大家庭！");
    }
}
