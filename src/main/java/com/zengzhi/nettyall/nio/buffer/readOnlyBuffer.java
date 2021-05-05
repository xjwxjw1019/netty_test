package com.zengzhi.nettyall.nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author xiejiawei
 * @Date 2021-04-07
 * @Time 21:30
 * 只读缓冲区
 */
@Slf4j
public class readOnlyBuffer {
    public static void main (String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        ByteBuffer asReadOnlyBuffer = buffer.asReadOnlyBuffer();
//        byte b = asReadOnlyBuffer.get(1);
//        b*=100;
//        asReadOnlyBuffer.put(1, b); 报错
        byte b = buffer.get(1);
        b*=100;
        buffer.put(1, b);
        log.info("改变内容以后的缓冲区：{}",asReadOnlyBuffer);
        while (asReadOnlyBuffer.remaining() > 0){
            log.info(String.valueOf(asReadOnlyBuffer.get()));
        }
    }
}
