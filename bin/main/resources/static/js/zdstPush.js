function zdstPush(userName,systemType){
	
	var websocket;
	if ('WebSocket' in window) {
	    websocket = new WebSocket("ws://localhost:8070/webSocketServer?userName="+userName+"&systemType="+systemType);
	} else if ('MozWebSocket' in window) {
	    websocket = new MozWebSocket("ws://localhost:8070/webSocketServer?userName="+userName+"&systemType="+systemType);
	} else {
	    websocket = new SockJS("http://localhost:8070/sockjs/webSocketServer?userName="+userName+"&systemType="+systemType);
	}
	
	var o = new Object();
	o.userName = userName;
	o.systemType = systemType;
	
	o.onmessage=function(fn){
		websocket.onmessage = function (evnt) {
			fn(evnt);
		};
	}
	o.onclose = function(evnt){
		websocket.onclose = function (evnt) {
		}
	}
	o.onopen = function(evnt){
		websocket.onopen = function (evnt) {
		};
	}
	o.onerror = function(evnt){
		websocket.onerror = function (evnt) {
		};
	}
	return o ;
	
}