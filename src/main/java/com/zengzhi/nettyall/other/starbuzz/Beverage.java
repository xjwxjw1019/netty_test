package com.zengzhi.nettyall.other.starbuzz;

/**
 *  饮料超级类
 */
public abstract class Beverage {
	String description = "Unknown Beverage";

	/**
	 * 获取饮料描述的方法
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 消费的方法
	 * @return
	 */
	public abstract double cost();
}
