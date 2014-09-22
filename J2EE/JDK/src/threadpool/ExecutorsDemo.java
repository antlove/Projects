package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsDemo {
	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Future<?> future = executorService.submit(new DefaultRunnable());
		System.out.println(future.get());
		
		// get the returned result from callable
		future = executorService.submit(new DefaultCallable());
		System.out.println(future.get());
		
		executorService.shutdown();
		
	}
}
