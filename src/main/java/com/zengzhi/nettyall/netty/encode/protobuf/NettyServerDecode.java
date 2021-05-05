package com.zengzhi.nettyall.netty.encode.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author xiejiawei
 * @Date 2021-04-18
 * @Time 21:21
 * 将byte数组解码成对象
 */
public class NettyServerDecode extends ByteToMessageDecoder {
    private Class target;
    NettyServerDecode(Class target){
        this.target = target;
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
       byte[] bytes = new byte[in.readableBytes()];
       in.readBytes(bytes);
        Object deSerializer = ProtoBufUtils.deSerializer(bytes, target);
        out.add(deSerializer);
    }
}
