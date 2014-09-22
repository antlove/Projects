package threadpool;

public class DefaultRunnable implements Runnable {
	private int count = 1;
	
	@Override
	public void run() {
		System.out.println("the count in default runnable is ["+count+++"]");
	}

}
