package com.zengzhi.nettyall.netty.encode.javapkprotobuf;

import com.zengzhi.nettyall.netty.encode.protobuf.Message;
import com.zengzhi.nettyall.netty.encode.protobuf.ProtoBufUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @author xiejiawei
 * @Date 2021-04-18
 * @Time 19:23
 */
@Slf4j
public class javapkprotobufTest1 {

    public static void main (String[] args) throws IOException {
        Message message = new Message("自定义序列化字节大小pk",new Date());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        log.info("jdk序列化后的大小{}",bytes.length);
        byte[] serializer = ProtoBufUtils.serializer(message);
        log.info("protosuff序列化后的大小{}",serializer.length);
    }
}
