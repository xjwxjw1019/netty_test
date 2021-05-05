package com.zengzhi.nettyall.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-04-17
 * @Time 10:52
 */
@Slf4j
public class NettyChatClient {
    public static void main (String[] args){
        // 创建线程组
        EventLoopGroup workgroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workgroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new MyMessageEncode());
                        // 添加接收服务端消息的处理逻辑
                        pipeline.addLast(new NettyChatClientHandle());
                    }
                });

        try {
            ChannelFuture channelFuture = bootstrap.connect("localhost",8888).sync();
            Channel channel = channelFuture.channel();
            log.info("{}连接服务端成功",channel.localAddress());
            log.info("现在可以给服务端发送消息了");
            for (int i = 0; i < 10; i++) {
                // 直接传输自定义的协议包
                String content = "wos";
                MyMessage myMessage = new MyMessage();
                byte[] bytes = content.getBytes(CharsetUtil.UTF_8);
                myMessage.setLength(content.length());
                myMessage.setContext(bytes);
                channel.writeAndFlush(myMessage);
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workgroup.shutdownGracefully();
        }

    }
}
