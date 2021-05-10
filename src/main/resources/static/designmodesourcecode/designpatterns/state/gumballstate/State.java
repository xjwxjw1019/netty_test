package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.state.gumballstate;

public interface State {
 
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
	
	public void refill();
}
