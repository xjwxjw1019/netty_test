package com.zengzhi.nettyall.netty.encode.java;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author xiejiawei
 * @Date 2021-04-11
 * @Time 16:38
 * // 自定义读写处理器
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**当客户端跟服务端连接上了之后，主动给服务端发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,netty server".getBytes(CharsetUtil.UTF_8));
        Message message = new Message("自定义的message对象",new Date());
        ctx.writeAndFlush(message);
    }

    /*** 当通道有读事件触发时，该方法将会自动触发
     * NIO那些判断逻辑都被Netty封装了
     * @param ctx
     * @param msg
     * @throws Exception
     * */
    @Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("收到服务端的消息：{}",byteBuf.toString(CharsetUtil.UTF_8));
    }

}
