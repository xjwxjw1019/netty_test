package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.observer.simple;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
