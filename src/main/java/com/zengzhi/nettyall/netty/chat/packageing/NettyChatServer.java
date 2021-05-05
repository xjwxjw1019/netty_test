package com.zengzhi.nettyall.netty.chat.packageing;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-04-17
 * @Time 10:52
 */
@Slf4j
public class NettyChatServer {
    public static void main (String[] args){
        // 创建两个线程池
        // 负责读写
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // 负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 创建启动器对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 设置相关参数
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new FixedLengthFrameDecoder(18));
//                        pipeline.addLast(new LineBasedFrameDecoder(18));
                        byte[] bytes = new byte[2];
                        bytes[0] = '$';
                        bytes[1] = '#';
                        ByteBuf byteBuf = Unpooled.copiedBuffer("$".getBytes());
                        pipeline.addLast(new DelimiterBasedFrameDecoder(18, true,false,byteBuf));
                        // 添加解码 转化为bytebuf->string
                        pipeline.addLast(new StringDecoder());
                        // 添加编码 转化为byteBuf
                        pipeline.addLast(new StringEncoder());
                        // 添加聊天的处理逻辑
                        pipeline.addLast(new NettyChatServerHandle());
                    }
                });
        // 绑定监听端口
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            log.info("开始在8888端口进行监听 ");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


}
