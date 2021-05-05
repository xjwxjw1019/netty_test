package com.zengzhi.nettyall.netty.chat.packageing;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-04-17
 * @Time 10:52
 */
@Slf4j
public class NettyChatServerHandle extends SimpleChannelInboundHandler<String> {

    /**
     * 创建一个集合来管理channel
     */
    private  static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /**
     * 当有客户端连接后，会触发此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 加入到集合中管理
        channelGroup.add(channel);
        log.info("{}客户端上线了",channel.remoteAddress());
        channelGroup.writeAndFlush("用户"+channel.remoteAddress()+"上线了");
    }

    /**
     * 当channel断开连接后触发此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 移除已经下线的channel
        channelGroup.remove(channel);
        log.info("{}客户端已经下线",channel.remoteAddress());
        channelGroup.writeAndFlush("用户"+channel.remoteAddress()+"下线了");

    }

    /**
     * 读到数据 则触发此方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取到当前的channel
        Channel currentChannel = ctx.channel();
        log.info("收到{}的消息{}",currentChannel.remoteAddress(),msg);
        // 判断如果我发的 应该是我说。。。。 他人 应该是 谁说。。
        channelGroup.forEach(channel -> {
            if(channel == currentChannel){
                currentChannel.writeAndFlush("我说："+msg);
            }else {
                channel.writeAndFlush("用户"+channel.remoteAddress()+"说："+msg);
            }

        });

    }

    /**
     * 有异常信息 触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
