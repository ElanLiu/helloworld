package utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimeoutUtil extends Timer{
	public TimeoutUtil(boolean isDaemon, int delay, final String msg){
		super(isDaemon);
		
		schedule(new TimerTask(){
			@Override
			public void run(){
				System.out.print(msg);
			}
		}, delay);
	}
	
	

}
