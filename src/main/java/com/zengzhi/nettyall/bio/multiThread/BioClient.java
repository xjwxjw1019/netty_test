package com.zengzhi.nettyall.bio.multiThread;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author xiejiawei
 * @Date 2021-04-05
 * @Time 11:15
 */
@Slf4j
public class BioClient {
    public static void main (String[] args){
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            // 服务端设置监听端口监听客户端请求
            try {
                socket = new Socket("localhost",8888);
                log.info("与服务端 建立连接");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // 基于io进行数据交互
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            log.info("请输入给服务端发送消息");
            writer.println(scanner.nextLine());
//            writer.println("客户端发消息了？？？你收到了吗");
            String content = reader.readLine();
            log.info("接收到服务端传过来的消息；{}",content);

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
