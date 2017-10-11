package cn.zdst.fireAlarm.usercenter.server.domain;

public class ResponseMsg {
	private String responseMessage;
    public ResponseMsg(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}
