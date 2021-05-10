package com.zengzhi.nettyall.other.starbuzz;

/**
 * @author xiejiawei
 * @Date 2021-05-09
 * @Time 11:49
 *  装饰者 具体的调料：摩卡
 */
public class Mocha extends CondimentDecorator{

    /**
     * 构造方法将饮料超级类作为入参
     * @param beverage
     */
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    /**
     * 消费的方法
     *
     * @return
     */
    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
