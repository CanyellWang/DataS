package com.wchy.common.util;


import com.wchy.common.task.TaskExecutor;

import java.util.List;

public class TaskThreadWrapper {

	
	public static boolean isAllTaskFinish(List<TaskExecutor> taskExecutors) {
		int finishQuantity = 0;
		for(TaskExecutor taskExecutor : taskExecutors) {
			if(taskExecutor.isFinish()) finishQuantity++; 
		}
		
		if(finishQuantity == taskExecutors.size()) return true;
		return false;
	}
	
}
