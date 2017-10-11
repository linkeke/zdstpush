package cn.zdst.fireAlarm.usercenter.server.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import cn.zdst.fireAlarm.usercenter.server.queue.PushQueue;
import cn.zdst.fireAlarm.usercenter.server.webSocket.MessageWebSocketHandler;

@Controller
public class WsController {
	private static final Logger logger = LoggerFactory
			.getLogger(WsController.class);
    
	@Autowired
    private MessageWebSocketHandler messageWebSocketHandler;


    @RequestMapping("/pushMsgToUser")
    @ResponseBody
    public String auditing(HttpServletRequest request,String userName,String systemType,String content) throws UnsupportedEncodingException{
        //无关代码都省略了
    	try{
    	request.setCharacterEncoding("utf-8");
        messageWebSocketHandler.sendMessageToUser(userName, systemType,new TextMessage(content));
        }catch(Exception ex){
        	logger.error("推送消息给个人失败，userName="+userName,ex);
        	return "error"; 
        }
        return "success";
    }
    @RequestMapping("/pushMsgToUsers")
    @ResponseBody
    public String all(HttpServletRequest request,String content){
    	try{
        messageWebSocketHandler.sendMessageToUsers( new TextMessage(content));
    	}catch(Exception ex){
    		logger.error("推送消息给所有用户失败，",ex);
    		return "error"; 
        }
        return "success";
    }
    @RequestMapping("/receiveMsg")
    @ResponseBody
    public String receiveMsg(HttpServletRequest request,@RequestParam Map<String,Object>  pushMsgDto){
    	try{
	    		PushQueue.offer(pushMsgDto);
	    }catch(Exception ex){
	    	logger.error("将消息放入队列失败，",ex);
	    	return "error"; 
        }
        return "success";
    }
    
    
}
