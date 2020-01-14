var WebsocketClient = function() {
	
	var usroId;//
	
	/**
	 * 判断是否有提醒框
	 */
	var isHasReminderBox = function(){
		return true;
	};
	
	/**
	 * socket对象
	 */
	var websocket=new Object();
	
	var init = function() {// init function
		//changeNewTip();//暂时
		if(!isHasReminderBox()){
			return;
		}
		
		 websocket = new WebSocket('ws://localhost/cb/websocketServer');
		 
		 /**
		  * 出错触发
		  */
		websocket.onerror = function(event) {
		};

		/*
		 * 连接服务器触发
		 */
		websocket.onopen = function(event) {
		};

		/*
		 * 接收信息触发
		 */
		websocket.onmessage = function(event) {
			changeNewTip();
		};

	};// init function

	
	/**
	 * 发送消息 message符合json格式{usroId:6,notyType:1,count:-1}
	 */
	var sendMessage = function(message){
		if (websocket==null) {
			return;
		}
		websocket.send(JSON.stringify(message)); 
	};

	var changeNewTip = function(){
		$.post("module/common/notice/changeNewTip", function(data){
			   $('#header_notification_bar').remove();
			   $('#newTipUl').prepend(data);
			 });
	};
	

	return{
		init:function(){
			init();
		},
		sendMessage:function(mgs){
			sendMessage(mgs);
		},
		changeNewTip:function(){
			changeNewTip();
		}
		
	};
}();