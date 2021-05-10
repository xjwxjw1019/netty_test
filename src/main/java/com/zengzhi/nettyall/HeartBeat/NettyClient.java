package com.zengzhi.nettyall.HeartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xiejiawei
 * @Date 2021-04-11
 * @Time 9:38
 */
@Slf4j
public class NettyClient {

    private static Bootstrap bootstrap;

    public static void main(String[] args) {
        // 创建一个线程池，用于读写交互
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个客户端对象
        bootstrap = new Bootstrap();
        // 设置相关参数
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new HeartBeatClientHandler());
                    }
                });
        //4.连接服务端
        try {
//            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
//            log.info("连接服务端成功"); //对通道关闭进行监听
//            Channel channel = future.channel();
//
//            future.channel().closeFuture().sync();
            // 断线重连
            reconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void reconnect() throws InterruptedException {
        ChannelFuture future = bootstrap.connect("localhost", 8888);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("连接服务器成功！");
                } else {
                    log.info("连接服务器失败！");
                //每隔1秒自动重新连接服务端，直到成功为止
                    future.channel().eventLoop().schedule(() -> {
                        log.info("1秒后，自动重新连接服务端");
                        try {
                            reconnect();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }, 1, TimeUnit.SECONDS);
                }
            }
        });
    }

}
