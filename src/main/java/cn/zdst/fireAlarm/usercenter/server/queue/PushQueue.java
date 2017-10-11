package cn.zdst.fireAlarm.usercenter.server.queue;


import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;


public class PushQueue {
	public static final ArrayBlockingQueue<Map<String,Object>> PushQueue= new ArrayBlockingQueue<Map<String,Object>>(2000);
	
	public static void offer(Map<String,Object> msg){
		PushQueue.offer(msg);
	}
	public static Map<String,Object> poll(){
		return PushQueue.poll();
	}
}
