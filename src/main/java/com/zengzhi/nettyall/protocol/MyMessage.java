package com.zengzhi.nettyall.protocol;

import lombok.Data;

/**
 * @author xiejiawei
 * @Date 2021-05-03
 * @Time 12:58
 */
@Data
public class MyMessage {

    /**
     * 定义一个消息长度
     */
    private int length;

    /**
     * 定义一个消息体
     */
    private byte[] context;
}
