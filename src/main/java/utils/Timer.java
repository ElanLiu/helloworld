package utils;

import java.sql.Date;
import java.util.Comparator;
import java.util.TreeSet;

public class Timer {
	
	/*
	 * 已排序的集合保存Timer负责的任务
	 * 使用一个比较函数，按照执行排序任务排序
	 */
	TreeSet tasks = new TreeSet();
	
	//timer，执行任务的线程
	TimerThread timer;
	
	
	/*
	 * 构造函数，默认不是daemon
	 */
	public Timer(){
		this(false);
	}
	
	/*
	 * 主构造函数
	 */
	public Timer(boolean isDaemon){
		timer = new TimerThread(isDaemon);
		timer.start();
	}
	
	/*
	 * 停止timer线程，放弃任务
	 */
	public void cancel(){
		//一次执行一个线程
		synchronized(tasks){
			//设置停止线程标志
			timer.pleaseStop();
			//放弃所有任务
			tasks.clear();
			//唤醒等待线程，如果他在wait中
			tasks.notify();
		}
	}
	
	//延迟后执行
	public void schedule(TimerTask task, long delay){
		task.schedule(System.currentTimeMillis() + delay, 0, false);
		schedule(task);
	}
	
	//指定时间执行
	public void schedule(TimerTask task, Date time){
		task.schedule(time.getTime(), 0, false);
		schedule(task);
	}
	
	//指定时间开始周期性执行
	public void schedule(TimerTask task, Date firstTime, long period){
		task.schedule(firstTime.getTime(), period, false);
		schedule(task);
	}
	
	//指定延迟后，周期执行
	public void schedule(TimerTask task, long delay, long period){
		task.schedule(System.currentTimeMillis() + delay, period, false);
		schedule(task);
	}
	
	//增加定时任务
	void schedule(TimerTask task){
		//一次只能一个线程修改任务
		synchronized(tasks){
			//增加任务
			tasks.add(task);
			//唤醒线程
			tasks.notify();
		}
	}
	
	
	/*
	 * 内部类，
	 */
	static class TimerTaskComparator implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			TimerTask t1 = (TimerTask) o1;
			TimerTask t2 = (TimerTask) o2;
			long diff = t1.nextTime - t2.nextTime;
			
			if(diff < 0)
				return -1;
			else if(diff > 0)
				return 1;
			else
				return 0;
		}
		public boolean equals(Object obj){
			return this == obj;
		}
		
	}

}
