package utils;

/**
 * 
 * @author yu.liuyly
 * 
 * 实现了timertask
 *
 */
public abstract class TimerTask implements Runnable{
	
	//是否取消状态
	boolean cancelled =false;
	
	//下一个定时是多久
	long nextTime = -1;
	
	//执行间隔是多久
	long period;
	
	//是否匀速执行
	boolean fixedRate;
	
	protected TimerTask(){}
	
	/*
	 * 取消任务执行，如果在执行，返回true，如果不在执行，返回false
	 */
	public boolean cancel(){
		if(cancelled)
			return false;
		
		if(nextTime == -1)
			return false;
		
		cancelled = true;
		return true;
	}
	
	/*
	 * 
	 */
	public long scheduleExecutionTime(){
		return nextTime;
	}
	
	/*
	 * 子类必须覆盖，提供执行代码
	 * @see java.lang.Runnable#run()
	 */
	public abstract void run();
	
	//由timer来告诉task定时情况
	void schedule(long nextTime, long period, boolean fixedRate){
		this.nextTime = nextTime;
		this.period = period;
		this.fixedRate = fixedRate;
	}
	
	boolean reschedule(){
		if(period == 0 || cancelled)
			return false;
		if(fixedRate)
			nextTime += period;
		else
			nextTime = System.currentTimeMillis() + period;
		return true;
	}

}
