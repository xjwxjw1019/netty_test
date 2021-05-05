package com.zengzhi.nettyall.netty.encode.java;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-04-11
 * @Time 16:38
 * // 自定义读写处理器
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 有客户端连接上会触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("有新的客户端连接上了");
    }

    /**
     * 当有数据可读的时候，会触发这个方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1.需要将msg转换为ByteBuf、
//        ByteBuf buf = (ByteBuf) msg;
        Message message = (Message) msg;
        log.info("接收到客户端发送来的消息:{}",message.toString());
    }

    /**
     *  当数据读取完毕之后，会触发该方法的执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //1.构建一个ByteBuf，作为传递的基本单位
        ByteBuf buffer = Unpooled.copiedBuffer("hello!this is netty server".getBytes(CharsetUtil.UTF_8));
        //2.发送数据给到客户端
        ctx.writeAndFlush(buffer);
    }
}
