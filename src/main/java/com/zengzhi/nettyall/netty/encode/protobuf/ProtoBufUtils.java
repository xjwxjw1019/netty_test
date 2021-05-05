package com.zengzhi.nettyall.netty.encode.protobuf;

import com.zengzhi.nettyall.netty.encode.java.Message;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiejiawei
 * @Date 2021-04-18
 * @Time 17:30
 *
 */
@Slf4j
public class ProtoBufUtils {

    private static Map<Class<?>,Schema<?>> schemaMap = new ConcurrentHashMap();


    /**
     *  将对象转化为字节数据
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serializer(T obj){
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            byte[] bytes = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
            return bytes;

        }catch (Exception e){
            throw new IllegalStateException(e.getMessage(),e);
        }finally {
            buffer.clear();
        }
    }

    public static <T> T deSerializer(byte[] bytes, Class<T> clazz){
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(),e);
        }

    }

    /**
     * 获取Schema
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaMap.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            log.info("create schema:{}",schema);
            if (schema != null) {
                schemaMap.put(clazz,schema);
            }
        }
        return schema;
    }

    public static void main (String[] args){
        Message message = new Message("自定义序列化工具",new Date());

        byte[] serializer = ProtoBufUtils.serializer(message);
        Message message1 = ProtoBufUtils.deSerializer(serializer, Message.class);
        log.info("message1 -----{}", message1.getContent());
    }
}
