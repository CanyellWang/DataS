package com.wchy.common.task;


import com.wchy.common.util.Configuration;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractRunner {
	private Configuration<String, Object> context;
	
	private ArrayBlockingQueue arrayBlockingQueue;
	
	public Configuration<String, Object> getContext() {
		return context;
	}

	public void setContext(Configuration<String, Object> context) {
		this.context = context;
	}

	public ArrayBlockingQueue getArrayBlockingQueue() {
		return arrayBlockingQueue;
	}

	public void setArrayBlockingQueue(ArrayBlockingQueue arrayBlockingQueue) {
		this.arrayBlockingQueue = arrayBlockingQueue;
	}
	
}
