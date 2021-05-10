package com.zengzhi.nettyall.HeartBeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author xiejiawei
 * @Date 2021-05-07
 * @Time 21:39
 */
@Slf4j
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<String> {

    /***
     *  记录超时的次数
     */
    private int timeoutCount = 0;

    /***
     * 记录最近一次的超时时间点
     */
    private long lastIdleTime = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        if ("heartBeat".equals(msg)) {
            log.info("收到{}的心跳包:{}", ctx.channel(), new Date());
            if (System.currentTimeMillis() - lastIdleTime >= 3000) {
                log.info("{}已经有一段时间表现非常稳定，超时次数清零", ctx.channel());
            }
        } else {
            log.info("非心跳信息不做处理");
        }
    }

    /**
     * 超时回调这个方法
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();
        IdleStateEvent e = (IdleStateEvent) evt;
        switch (e.state()) {
            case READER_IDLE:
                Date now = new Date();
                log.info("{}:出现了一次心跳超时{}", channel, now);
                timeoutCount++;
                lastIdleTime = now.getTime();
                break;
            default:
                break;
        }
        if (timeoutCount >= 3) {
            log.info("{}心跳超时已经达到了3次，将关闭该连接", channel);
            channel.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}上线了", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}下线了", ctx.channel());
    }
}
