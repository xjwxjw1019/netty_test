package com.zengzhi.nettyall.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-04-17
 * @Time 16:19
 */
@Slf4j
public class NettyChatClientHandle extends SimpleChannelInboundHandler<String> {

    /**
     * 当有可读的数据 触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info(msg);
    }
}
