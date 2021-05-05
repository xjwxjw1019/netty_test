package com.zengzhi.nettyall.netty.chat.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

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
                        // 添加编码 转化为byteBuf
                        pipeline.addLast(new StringEncoder());
                        // 添加解码 转化为bytebuf->string
                        pipeline.addLast(new StringDecoder());
                        // 添加接收服务端消息的处理逻辑
                        pipeline.addLast(new NettyChatClientHandle());
                    }
                });

        try {
            ChannelFuture channelFuture = bootstrap.connect("localhost",8888).sync();
            Channel channel = channelFuture.channel();
            log.info("{}连接服务端成功",channel.localAddress());
            log.info("现在可以给服务端发送消息了");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workgroup.shutdownGracefully();
        }

    }
}
