package com.zengzhi.nettyall.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-05-21
 * @Time 22:19
 */
@Slf4j
public class WebSocketServer {

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
                        //1,添加Netty提供的处理Http请求的相关Handler
                        // 将请求和应答消息做编解码
                        // HttpServerCodec extends CombinedChannelDuplexHandler<HttpRequestDecoder, HttpResponseEncoder>
                        pipeline.addLast(new HttpServerCodec());
                        //将Http消息的多个部分组成一个完整的HTTP消息
                        // 1024表示聚合支持的最大长度
                        pipeline.addLast(new HttpObjectAggregator(1024));
                        //支持传输大文件，在此案例是非必须的
                        pipeline.addLast(new ChunkedWriteHandler());
                        //2,添加Netty提供的处理websocket协议的相关Handler
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        //添加具体的业务逻辑处理的Handler
                        pipeline.addLast(new WebSocketHandler());
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
