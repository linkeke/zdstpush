package cn.zdst.fireAlarm.usercenter.server.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cn.zdst.fireAlarm.usercenter.server.thread.PushThread;
import cn.zdst.fireAlarm.usercenter.server.webSocket.MessageWebSocketHandler;

@Component
public class PushListener implements CommandLineRunner {
	private static final Logger logger = LoggerFactory
			.getLogger(PushListener.class);
	@Autowired
    private MessageWebSocketHandler messageWebSocketHandler;
    @Override
    public void run(String... args) throws Exception {
    	// 创建一个可重用固定线程数的线程池
       // ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i = 0 ;i<10;i++){
        	PushThread thread = new PushThread(messageWebSocketHandler);
        	thread.setName("推送线程-"+i);
        	thread.start();
        	logger.debug(thread.getName()+",启动成功");
        }
        
    }

}
