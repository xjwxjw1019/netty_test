package com.zengzhi.nettyall.other.starbuzz;

/**
 * @author xiejiawei
 * @Date 2021-05-09
 * @Time 11:42
 * 调料的被装饰者对象
 */
public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;
    /**
     * 需要写这个方法吗？？
     * @return
     */
    public abstract String getDescription();
}
