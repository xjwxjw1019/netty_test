package com.zengzhi.nettyall.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiejiawei
 * @Date 2021-05-03
 * @Time 18:57
 */
@Slf4j
public class MyMessageEncode extends MessageToByteEncoder<MyMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessage msg, ByteBuf out) throws Exception {
        log.info("自定义编码器被调用了");
        out.writeShort(2);
        out.writeByte(3);
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContext());
    }
}
