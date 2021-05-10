package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.strategy.challenge;

public class Email implements ShareStrategy {
	public void share() {
		System.out.println("I'm emailing the photo");
	}
}
