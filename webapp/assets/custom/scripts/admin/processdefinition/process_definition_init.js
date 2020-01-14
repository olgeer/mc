define(["jquery"], function($){
	var initEvent = function() {
		// 设置保存事件
		$("#updateProcessdeFinitionStatus").on('click', function(e) {
			e.preventDefault();
			Layout.ajaxSubmit("#processDefinition", "#updateProcessdeFinitionStatus");
		});
		
	};
	
	var init = function(){
//		switchInit();
		initEvent();
	};
	
	return {
		init : init 
	};
	
});
