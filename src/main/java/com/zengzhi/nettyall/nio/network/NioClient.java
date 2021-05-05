package com.zengzhi.nettyall.nio.network;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author xiejiawei
 * @Date 2021-04-08
 * @Time 22:14
 */
@Slf4j
public class NioClient {
    public static void main(String[] args) throws IOException {
        // 创建
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        ;
        //由于是非阻塞 可能连接成功 也可能失败
        if (!socketChannel.connect(new InetSocketAddress(8888))) {
            while (!socketChannel.finishConnect()) {
                log.info("正在连接服务器中....");
            }
        }
        log.info("连接服务器成功！");
        //实现给服务端发送消息
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String content = scanner.nextLine();
            //转换为ByteBuffer进行发送
            ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes());
            //发送数据
            socketChannel.write(byteBuffer);
        }
    }

}
