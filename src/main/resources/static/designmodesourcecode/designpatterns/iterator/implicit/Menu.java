package com.zengzhi.nettyall.other.designmodesourcecode.designpatterns.iterator.implicit;

import java.util.Iterator;

public interface Menu {
	public Iterator<MenuItem> createIterator();
}
