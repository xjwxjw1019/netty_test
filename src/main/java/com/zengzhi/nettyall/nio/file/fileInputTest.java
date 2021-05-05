package com.zengzhi.nettyall.nio.file;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiejiawei
 * @Date 2021-04-06
 * @Time 21:23
 */
@Slf4j
public class fileInputTest {

    public static void main (String[] args) throws IOException {
      // 1.从fileInputStream中获取channle
        FileInputStream fileInputStream = new FileInputStream("D:\\IdeaSpace\\pagoda\\netty-all\\src\\main\\java\\com\\zengzhi\\nettyall\\nio\\file\\name.txt");
        FileChannel channel = fileInputStream.getChannel();
        // 2. 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(9);
        // 3. 将数据从channle读取到buffer
        log.info("读取前的buffer：{}",buffer);
        channel.read(buffer);
        log.info("读取后的buffer：{}",buffer);
        buffer.flip();
        // 4，读取缓冲区的数据
        while (buffer.remaining() > 0){
            byte b = buffer.get();
            log.info(String.valueOf((char) b));
        }

        channel.close();

    }
}
