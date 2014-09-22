package threadpool;

import java.util.concurrent.Callable;

public class DefaultCallable implements Callable<Object> {
	private int count =1;
	
	@Override
	public Object call() throws Exception {
		
		return count++;
	}

}
