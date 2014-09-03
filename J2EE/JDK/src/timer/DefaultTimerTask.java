package timer;

import java.util.Random;
import java.util.TimerTask;

public class DefaultTimerTask extends TimerTask{

	private long count = 1;
	private long prevInvokeTime = 0;
	
	
	@Override
	public void run() {
		long currentInvokeTime = System.currentTimeMillis();
		
		if(prevInvokeTime>0){
			System.out.println("invoke this execution after ["+(currentInvokeTime-prevInvokeTime)+"]");
		}
		
		try {
			Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("execution count is ["+count+++"]");
		
		prevInvokeTime = currentInvokeTime;
		
	}

}
