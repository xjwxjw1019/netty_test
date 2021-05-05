package com.zengzhi.nettyall.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xiejiawei
 * @Date 2021-05-03
 * @Time 19:14
 */
@Slf4j
public class MyMessageDncode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("调用的自定义的解码器");
        // 协议头默认采用4个字节 表示  消息体的大小
        int readableBytes = in.readableBytes();
        int readShort = in.readShort();
        int readByte = in.readByte();
        if (readableBytes > 4) {
            // 记录读指针的位置
            ByteBuf byteBuf = in.markReaderIndex();
            log.info(String.valueOf(in));
            int length = in.readInt();
            log.info(String.valueOf(in));
            if (readableBytes < length) {
                //重置读指针的位置 上面已经读了4个字节，要重置
                in.resetReaderIndex();
                log.info("数据包未完整传输");
                return;
            }
            // 正常情况
            byte[] bytes = new byte[length];
            in.readBytes(bytes);
            MyMessage myMessage = new MyMessage();
            myMessage.setLength(length);
            myMessage.setContext(bytes);
            out.add(myMessage);

        }

    }
}
