package com.zengzhi.nettyall.other.observerPattern;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 21:34
 */
public interface Subject {

    /**
     * 注册观察者的方法
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 移除观察者的方法
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知观察者的方法
     */
    void notifyObserver();
}
