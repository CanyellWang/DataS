package com.wchy.common.task;

public class TaskRunable implements Runnable {
	private TaskExecutor taskExecutor;
	public TaskRunable(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	@Override
	public void run() {
		taskExecutor.doStart();
	}
	
}
