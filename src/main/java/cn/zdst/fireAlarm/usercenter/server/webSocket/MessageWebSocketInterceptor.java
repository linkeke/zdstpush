package cn.zdst.fireAlarm.usercenter.server.webSocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class MessageWebSocketInterceptor implements HandshakeInterceptor {
	 @Override
	 public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
	 if (request instanceof ServletServerHttpRequest) {
		 ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
         HttpSession session = servletRequest.getServletRequest().getSession(false);
             //使用userName区分WebSocketHandler，以便定向发送消息
        	 String userName = ((ServletServerHttpRequest) request).getServletRequest().getParameter("userName");
        	 String systemType = ((ServletServerHttpRequest) request).getServletRequest().getParameter("systemType");
             //String userName = (String) session.getAttribute("username");
             attributes.put("userName",userName+"_"+System.currentTimeMillis());//加上时间戳字符串，确保一个账号可以同时登陆多次
             attributes.put("systemType",systemType);
	 }
	 return true;
	 }

	 @Override
	 public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

	 }
	}