package com.wchy.common.task;


import com.wchy.common.context.TaskContext;
import com.wchy.common.util.Configuration;

import java.util.concurrent.ArrayBlockingQueue;

public class TaskExecutor {

	private Configuration<String, Object> context;
	
	private boolean start = false;
	
    private Thread readerThread;

    private Thread writerThread;
	
	
	public TaskExecutor(Configuration<String, Object> context) {
		this.context = context;
		
		
		ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10000);
		AbstractRunner abstractRunnerReader = (AbstractRunner) TaskContext.getReader((String)context.get("reader-type"));
		ReaderTask readerTask = new ReaderTask(abstractRunnerReader);
		abstractRunnerReader.setArrayBlockingQueue(arrayBlockingQueue);
		abstractRunnerReader.setContext(context);
		readerThread = new Thread(readerTask);
		
		
		AbstractRunner abstractRunnerWriter = (AbstractRunner)TaskContext.getWriter((String)context.get("writer-type"));
		abstractRunnerWriter.setArrayBlockingQueue(arrayBlockingQueue);
		abstractRunnerWriter.setContext(context);
		WriterTask writerTask = new WriterTask(abstractRunnerWriter);
		writerThread = new Thread(writerTask);
		
	}
	
	public void doStart() {

		readerThread.start();
		
		writerThread.start();
	}
	
	
	public boolean isFinish() {
		if(!readerThread.isAlive() && !writerThread.isAlive() && start)  return true;
		
		return false;
	}
	
	
	class ReaderTask  implements Runnable {
		private AbstractRunner abstractRunner;
		
		public ReaderTask(AbstractRunner abstractRunner) {
			this.abstractRunner = abstractRunner;
		}
		
		@Override
		public void run() {
			start = true;
			IReader reader = (IReader)abstractRunner;
			reader.read();
		}

	}
	
	
	
	
	class WriterTask  implements Runnable {
		private AbstractRunner abstractRunner;
		
		WriterTask(AbstractRunner abstractRunner) {
			this.abstractRunner = abstractRunner;
		}
		@Override
		public void run() {
			start = true;
			IWriter writer = (IWriter)abstractRunner;
			writer.writer();
		}
		
	}
	
}
