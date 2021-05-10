package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.strategy;

public class FakeQuack implements QuackBehavior {
	public void quack() {
		System.out.println("Qwak");
	}
}
