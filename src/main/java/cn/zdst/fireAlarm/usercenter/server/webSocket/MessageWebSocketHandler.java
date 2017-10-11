package cn.zdst.fireAlarm.usercenter.server.webSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
@Component
public class MessageWebSocketHandler implements WebSocketHandler {
	private static final ArrayList<WebSocketSession> users;
	private static final Logger logger;

    static {
        users = new ArrayList<WebSocketSession>();
        logger = LoggerFactory.getLogger(MessageWebSocketHandler.class);
    }
	 @Override
	 public void afterConnectionEstablished(WebSocketSession session) throws IOException {
		 Map<String, Object> attributes = session.getAttributes();
		 logger.debug("connect to the websocket success......");
	        users.add(session);
	        String userName = (String) session.getAttributes().get("userName");
	        String systemType = (String) session.getAttributes().get("systemType");
	        if(userName!= null&&systemType!=null){
	            //查询未读消息
	           logger.debug(userName+"连接websocket成功！");
	          // session.sendMessage(new TextMessage("发送未收到的消息"));
	        }
	 }

	 @Override
	 public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
		 String payload = (String) message.getPayload();
		 logger.debug("服务端收到客户端发送的消息："+payload);
	 }

	 @Override
	 public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
		 if(session.isOpen()){
	            session.close();
	        }
	        logger.debug("websocket connection closed......");
	        users.remove(session);
	 }

	 @Override
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
		 logger.debug("websocket connection closed......");
	     users.remove(session);
	 }

	 @Override
	 public boolean supportsPartialMessages() {
	 return false;
	 }
	 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName,String systemType, TextMessage message) {
        for (WebSocketSession user : users) {
        	String sessionUser = (String) user.getAttributes().get("userName");
        	String sessionUserName = sessionUser.split("_")[0];
            if (sessionUserName.equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
	}