package com.wchy.common.util;

public class DateUtil {

	private static Long curTime = null;

	public static Long getCurTime() {
		return curTime;
	}

	public static void setCurTime(Long curTime) {
		DateUtil.curTime = curTime;
	}
	
	
}
