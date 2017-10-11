package cn.zdst.fireAlarm.usercenter.server.thread;

import java.util.Map;

import org.springframework.web.socket.TextMessage;

import com.owl.wifi.util.GsonUtil;

import cn.zdst.fireAlarm.usercenter.server.queue.PushQueue;
import cn.zdst.fireAlarm.usercenter.server.webSocket.MessageWebSocketHandler;

public class PushThread extends Thread{
	private MessageWebSocketHandler messageWebSocketHandler;
    public PushThread(MessageWebSocketHandler messageWebSocketHandler){
    	this.messageWebSocketHandler=messageWebSocketHandler;
    }
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Map<String,Object> pushMsgDto = PushQueue.poll();
			if(pushMsgDto!=null){
				String userName = (String) pushMsgDto.get("userName");
				String systemType = (String) pushMsgDto.get("systemType");
				messageWebSocketHandler.sendMessageToUser(userName, systemType, new TextMessage(GsonUtil.objectToJson(pushMsgDto)));
			}
		}
	}

}
