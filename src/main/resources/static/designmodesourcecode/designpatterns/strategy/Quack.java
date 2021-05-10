package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.strategy;

public class Quack implements QuackBehavior {
	public void quack() {
		System.out.println("Quack");
	}
}
