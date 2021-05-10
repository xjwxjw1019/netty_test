package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.observer.weather;

public interface Observer {
	public void update(float temp, float humidity, float pressure);
}
