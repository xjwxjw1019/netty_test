package com.zengzhi.nettyall.OptionalTest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * @author xiejiawei
 * @Date 2021-05-01
 * @Time 11:43
 */
@Data
@NoArgsConstructor
public class Xjw implements Supplier<User> {

    @Override
    public User get() {
        return new User("1","没有值get到");
    }
}
