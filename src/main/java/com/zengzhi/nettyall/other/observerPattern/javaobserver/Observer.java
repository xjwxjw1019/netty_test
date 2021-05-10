package com.zengzhi.nettyall.other.observerPattern.javaobserver;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 21:35
 */
public interface Observer {

    /**
     * 修改的方法
     * @param temp
     * @param humidity
     * @param pressure
     */
    void update(float temp, float humidity, float pressure);
}
