package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.strategy;

public class Squeak implements QuackBehavior {
	public void quack() {
		System.out.println("Squeak");
	}
}
