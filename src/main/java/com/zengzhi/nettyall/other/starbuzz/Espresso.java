package com.zengzhi.nettyall.other.starbuzz;

/**
 * @author xiejiawei
 * @Date 2021-05-09
 * @Time 11:39
 * 一种咖啡的类型
 */
public class Espresso extends Beverage {

    public Espresso (){
        description = "Espresso";
    }
    /**
     * 抽象的消费的方法
     * @return
     */
    @Override
    public double cost() {
        return 0.55;
    }

}
