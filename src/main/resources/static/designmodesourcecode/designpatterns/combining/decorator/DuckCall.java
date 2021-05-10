package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.combining.decorator;

public class DuckCall implements Quackable {
 
	public void quack() {
		System.out.println("Kwak");
	}
 
	public String toString() {
		return "Duck Call";
	}
}
