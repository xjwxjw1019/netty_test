package com.zengzhi.nettyall.netty.encode.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejiawei
 * @Date 2021-04-18
 * @Time 11:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    private String content;

    private Date createTime;
}
