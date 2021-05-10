package com.zengzhi.nettyall.other.starbuzz;

/**
 * @author xiejiawei
 * @Date 2021-05-09
 * @Time 12:52
 */
public class StarbuzzCoffee {
    public static void main(String[] args) {

        // 第一种饮料类型
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + "$" + beverage.cost());

        // 第二种饮料类型
        Beverage beverage2 = new DarkRoast();
        // 加入调料
//        beverage2 = new Mocha(beverage2);
        beverage2 = new Milk(beverage2);

        System.out.println(beverage2.getDescription() + "$" + beverage2.cost());



    }

}
