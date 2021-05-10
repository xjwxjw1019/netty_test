package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.combining.adapter;

public class DuckCall implements Quackable {
	public void quack() {
		System.out.println("Kwak");
	}
}
