package com.zengzhi.nettyall.netty.encode.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xiejiawei
 * @Date 2021-04-18
 * @Time 21:22
 * 将对象编码为byte数组
 */
public class NettyClientEncode extends MessageToByteEncoder {
    private Class clazz;
    NettyClientEncode(Class target){
        this.clazz = target;
    }
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if(msg != null && msg.getClass().equals(clazz)){
            out.writeBytes(ProtoBufUtils.serializer(msg));
        }
    }
}
