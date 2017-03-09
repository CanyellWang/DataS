package com.wchy.common.task;


import com.wchy.common.util.Configuration;
import com.wchy.common.util.RdbmsRangeSplitWrap;
import com.wchy.common.util.TaskThreadWrapper;
import com.wchy.template.BaseSqlTemplate;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskContainer {

	public void doStart(Configuration<String, Object> context) {
    	List<String> rangeList = slice(context);
		
		List<TaskExecutor> taskExecutors = new ArrayList<TaskExecutor>((Integer)context.get("threadNumber"));
		ExecutorService executor = Executors.newFixedThreadPool((Integer)context.get("threadNumber"));
		for(int i = 0; i < (Integer)context.get("threadNumber"); i++) {
			//抽时间提出去
			Configuration<String, Object> cloneContext = context.clone();
			cloneContext.put("querySql", context.get("querySql_templete").toString() + " and " + rangeList.get(i));
			
			TaskExecutor taskExecutor = new TaskExecutor(cloneContext);
			taskExecutors.add(taskExecutor);
			TaskRunable taskRunable = new TaskRunable(taskExecutor);
			executor.submit(taskRunable);
		}
		
		while(true) {
			if(TaskThreadWrapper.isAllTaskFinish(taskExecutors)) {
				break;
			}
		}
		
		executor.shutdown();
	}
	
	
	private List<String> slice(Configuration<String, Object> context) {
    	Pair<Object, Object> minMaxPK = BaseSqlTemplate.getPKRange((String)context.get("minMaxSql"), 1, 1000);
    	List<String> rangeList = RdbmsRangeSplitWrap.splitAndWrap(
                new BigInteger(minMaxPK.getLeft().toString()),
                new BigInteger(minMaxPK.getRight().toString()),
                Integer.parseInt(context.get("threadNumber").toString()),
                (String)context.get("splitPkName"));
    	
    	return rangeList;
	}
	
	
}
