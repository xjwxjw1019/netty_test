package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.decorator.pizza;

public abstract class ToppingDecorator extends Pizza {
	Pizza pizza;
	public abstract String getDescription();
}
