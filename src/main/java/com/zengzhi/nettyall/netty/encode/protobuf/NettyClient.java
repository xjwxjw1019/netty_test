package com.zengzhi.nettyall.netty.encode.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-04-11
 * @Time 9:38
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) {
        // 创建一个线程池，用于读写交互
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个客户端对象
        Bootstrap bootstrap = new Bootstrap();
        // 设置相关参数
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        pipeline.addLast(new NettyClientEncode(Message.class));
                        pipeline.addLast(new NettyClientHandler());
                    }
                });
        //4.连接服务端
        try {
            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
            log.info("连接服务端成功"); //对通道关闭进行监听
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

}
