package com.zengzhi.nettyall.bio.multiThread;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiejiawei
 * @Date 2021-04-05
 * @Time 11:15
 * serverSocket
 */
@Slf4j
public class BioServer {

    public static void main (String[] args){
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            // 服务端设置监听端口监听客户端请求
            serverSocket = new ServerSocket(8888);
            log.info("服务器开始监听8888端口");
            // 接收客户端请求
            while (true) {
                // 阻塞客户端 接口请求
                socket = serverSocket.accept();
                log.info("接收到客户端的连接请求{}", socket);
                //重新启动一个线程进行处理
                new Thread(new BioServerHandle(socket)).start();
            }
        }catch (Exception e){

        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
