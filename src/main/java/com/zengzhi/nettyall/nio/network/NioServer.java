package com.zengzhi.nettyall.nio.network;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xiejiawei
 * @Date 2021-04-08
 * @Time 21:06
 *
 */
@Slf4j
public class NioServer {
    public static void main (String[] args) throws IOException {
        // 1.创建serverSocketChannel对象，并设置监听端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        // 2.设置为非阻塞通道
        serverSocketChannel.configureBlocking(false);
        // 3.创建一个selector对象
        Selector selector = Selector.open();
        // 4.将serverSocketChannel注册到selector上，并设置客户端的连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("服务端在8888端口开始监听");

        while (true){
            // 5,阻塞等待需要处理的就绪事件
            selector.select();
            // 6.获取selector中的就绪列表
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //根据不同的key处理不同的事件
                if (key.isAcceptable()) {
                    //连接事件
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 通过服务端的channel创建客户端的channel
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    // 注册读事件用来接收客户端发送的数据
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    log.info("{}客户端连接成功",socketChannel);
                }else if(key.isReadable()){
                    //读取事件
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 创建byteBuffer读取通道中传输的数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int length= socketChannel.read(buffer);
                    if (length > 0) {
                        log.info("收到客户端发送来的消息：{}",new String(buffer.array()));
                    }

                }
                //删除本次处理的selectKey，避免下次select重复处理
                iterator.remove();
            }

        }
    }
}
