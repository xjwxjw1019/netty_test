package com.zengzhi.nettyall.other.starbuzz;

/**
 * @author xiejiawei
 * @Date 2021-05-09
 * @Time 11:39
 * 一种咖啡的类型
 */
public class DarkRoast extends Beverage {

    DarkRoast(){
        description = "DarkRoast";
    }
    /**
     * 抽象的消费的方法
     * @return
     */
    @Override
    public double cost() {
        return .33 ;
    }

}
