package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.decorator.starbuzz;

public abstract class CondimentDecorator extends Beverage {
	Beverage beverage;
	public abstract String getDescription();
}
