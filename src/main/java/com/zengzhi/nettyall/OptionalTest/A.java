package com.zengzhi.nettyall.OptionalTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xiejiawei
 * @Date 2021-05-01
 * @Time 12:36
 */
public class A {
    public static void main (String[] args){
        List<String> aa=new ArrayList();
        aa.add("aaa");
        aa.add("abbb");
        aa.add("accc");
        aa.add("ddd");
        System.out.println("原始值："+aa);


        Optional<String> largest=aa.stream().max(String::compareToIgnoreCase);

        System.out.println(largest);

        List<String> bb=new ArrayList();
        largest.ifPresent(bb::add);

        System.out.println("ifPresent 的用法："+bb);


        Optional<Boolean> added=largest.map(bb::add);
        System.out.println("会有返回值处理:"+added.get());








    }
}
