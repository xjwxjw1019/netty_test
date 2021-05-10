package com.zengzhi.nettyall.HeartBeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author xiejiawei
 * @Date 2021-05-10
 * @Time 21:58
 */
@Slf4j
public class HeartBeatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送心跳吧
        Channel channel = ctx.channel();
        Random random = new Random();
            while (channel.isActive()){
                int num = random.nextInt(6);
                Thread.sleep(num*1000);
                channel.writeAndFlush("heartBeat\n");
                channel.flush();
            }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}下线了",ctx.channel());
        NettyClient.reconnect();
    }
}
