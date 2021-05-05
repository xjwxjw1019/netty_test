package com.zengzhi.nettyall.nio.file;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiejiawei
 * @Date 2021-04-06
 * @Time 22:23
 */
@Slf4j
public class fileOutTest {
    public static void main (String[] args) throws IOException {
        //1，从FileOutputStream获取Channle
        FileOutputStream outputStream = new FileOutputStream( "D:\\IdeaSpace\\pagoda\\netty-all\\src\\main\\java\\com\\zengzhi\\nettyall\\nio\\file\\name.txt");
        FileChannel channel = outputStream.getChannel();
        //2，创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 99);
        log.info("写入完毕之后：{}",buffer);
        //关键点
        buffer.flip();
        log.info("flip之后：{}",buffer);
        //3，将Buffer通过Channle写入到文件中
        channel.write(buffer);
        channel.close();
    }
}
