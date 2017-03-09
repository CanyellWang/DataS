package com.wchy.common.util;

public class NullObject {

	public static NullObject build() {
		return new NullObject();
	}
	
	public static boolean isType(Object obj) {
		return (NullObject.class == obj.getClass());
			
	}
}
