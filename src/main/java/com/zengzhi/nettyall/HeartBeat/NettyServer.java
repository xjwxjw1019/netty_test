package com.zengzhi.nettyall.HeartBeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xiejiawei
 * @Date 2021-04-11
 * @Time 9:38
 */
@Slf4j
public class NettyServer {

    public static void main (String[] args){
        // 1.创建两个线程组
        // 一个负责连接请求/
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 一个负责读写请求
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // 2.创建一个服务端启动对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 3. 设置相关参数
        // 采用主从线程组
        serverBootstrap.group(bossGroup, workGroup)
                // 设置通道类型为NIO类型
                .channel(NioServerSocketChannel.class)
                // 设置从线程的处理逻辑
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 添加自定义的处理逻辑
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new LineBasedFrameDecoder(18));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        //添加一个检查心跳超时的Handler
                        // 当3秒未接受到客户端的心跳包时，则出现超时
                        pipeline.addLast(new IdleStateHandler(3,0,0, TimeUnit.SECONDS));
                        pipeline.addLast(new HeartBeatServerHandler());
                    }
                });
        // 绑定端口
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            log.info("服务端已经启动，在8888端口进行监听");
            // 当服务端的channel关闭后，才会关闭channelFuture
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
