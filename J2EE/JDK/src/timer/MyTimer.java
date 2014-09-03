package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	
	public static void executionOnce(){
		Timer timer = new Timer();
		
		TimerTask task = new DefaultTimerTask();
		
		timer.schedule(task, 1000);
	}
	
	public static void executionAtSpecifiedTime(){
		Timer timer = new Timer();
		
		TimerTask task = new DefaultTimerTask();
		
		timer.schedule(task, new Date());
	}
	
	public static void executionInterval(){
		Timer timer = new Timer();
		
		TimerTask task = new DefaultTimerTask();
		
		timer.schedule(task, 1000,1000);
	}
	
	public static void executionIntervalAtSpecifiedTime(){
		Timer timer = new Timer();
		
		TimerTask task = new DefaultTimerTask();
		
		timer.schedule(task, new Date(),1000);
	}
	
	public static void executionFixedInterval(){
		Timer timer = new Timer();
		
		TimerTask task = new DefaultTimerTask();
		
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	public static void executionFixedIntervalSpecifiedTime(){
		Timer timer = new Timer();
		
		TimerTask task = new DefaultTimerTask();
		
		timer.scheduleAtFixedRate(task, new Date(), 1000);
	}
	
	
	public static void main(String[] args) {
//		executionOnce();
//		executionAtSpecifiedTime();
//		executionInterval();
//		executionIntervalAtSpecifiedTime();
//		executionFixedInterval();
		executionFixedIntervalSpecifiedTime();
	}
}
