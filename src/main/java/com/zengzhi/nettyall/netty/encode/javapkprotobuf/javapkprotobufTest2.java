package com.zengzhi.nettyall.netty.encode.javapkprotobuf;

import com.zengzhi.nettyall.netty.encode.protobuf.Message;
import com.zengzhi.nettyall.netty.encode.protobuf.ProtoBufUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * @author xiejiawei
 * @Date 2021-04-18
 * @Time 19:23
 */
@Slf4j
public class javapkprotobufTest2 {

    public static void main (String[] args) throws IOException {
        Message message = new Message("自定义序列化字节大小pk",new Date());
        Instant start = Instant.now();
         for (int i = 0; i < 10000000; i++) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
        }
        Instant end = Instant.now();
        log.info("jdk序列化后1000万的时间{}", Duration.between(start,end).toMillis());
        start = Instant.now();
        for (int i = 0; i < 10000000; i++) {
            ProtoBufUtils.serializer(message);
        }
        end = Instant.now();
        log.info("protosuff序列化1000万时间{}",Duration.between(start,end).toMillis());
    }
}
