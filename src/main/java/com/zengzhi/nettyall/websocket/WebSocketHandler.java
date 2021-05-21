package com.zengzhi.nettyall.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-05-21
 * @Time 22:39
 */
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //1.获取到客户端发送过来的消息
        String text = msg.text();
        Channel channel = ctx.channel();
        log.info("{}说:{}", channel.remoteAddress(), text);
        //2.将消息推送给其他的客户端
        channelGroup.writeAndFlush(new TextWebSocketFrame(channel.remoteAddress() + "说：" + text));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        log.info("{}上线了：", ctx.channel());
        log.info("当前在线人数：{}", channelGroup.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}下线了", ctx.channel());
    }
}
