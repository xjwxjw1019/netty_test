package com.zengzhi.nettyall.bio.multiThread;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiejiawei
 * @Date 2021-04-05
 * @Time 12:38
 */
@Slf4j
public class BioServerHandle implements Runnable {

    private Socket socket;

    public BioServerHandle(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            // 服务端设置监听端口监听客户端请求
            serverSocket = new ServerSocket(8888);
            log.info("服务器开始监听8888端口");

            // 基于io进行数据交互
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String content = reader.readLine();
            log.info("接收到客户端传过来的消息；{}",content);
            writer.println("服务端已接收到消息");
        }catch (Exception e){

        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(writer != null){
                writer.close();
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
