package com.bham.threadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.bham.executorService.WorkerThread;

public class WorkerPool {
	public static void main(String[] args) throws InterruptedException {
		RejectedExecutionHandlerImpl rejectHander = new RejectedExecutionHandlerImpl();
		ThreadFactory threadfactory = Executors.defaultThreadFactory();
		ThreadPoolExecutor executerPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),threadfactory,rejectHander);
		
		//start the monitoring thread
		MyMonitorThread monitor = new MyMonitorThread(executerPool, 3);
		Thread monitorthread = new Thread(monitor);
		monitorthread.start();
		
		//submit work to the thread pool
		for(int i=0;i<10;i++){
			executerPool.execute(new WorkerThread("cmd"+i));
		}
		
		Thread.sleep(30000);
		//shut down the pool
		executerPool.shutdown();
		//shut down the monitor thread
		Thread.sleep(5000);
		monitor.shutdown();
	}
}
