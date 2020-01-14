/**
 * 有带有附件上传的表单提交方法
 */
var ButtonClickEvent = function(){
	
		btnClick = function(btnId,containerId){
			var url = $(btnId).attr("href");
			$.ajax({
				   type: "POST",
				   url: url, 
				   success: function(res){
					   Metronic.stopPageLoading();
						if (res.sStatus == true) {
	                   	Metronic.alert({
	                   		type: 'success', 
	                   		icon: 'check', 
	                   		message: res.sMessage, 
	                   		container: $(containerId), 
	                   		place: 'prepend'
	                   	});
	                   }else if (res.sStatus == false) {
	                   	Metronic.alert({
	                   		type: 'danger', 
	                   		icon: 'warning', 
	                   		message: res.sMessage, 
	                   		container: $(containerId), 
	                   		place: 'prepend'
	                   	});
	                   }
				   },
				   error : function(res) {
						Metronic.stopPageLoading();
						Metronic.alert({
		            		type: 'danger', 
		            		icon: 'warning', 
		            		message: '内部错误...', 
		            		container: $(containerId), 
		            		place: 'prepend'
		            	});
					}
				
				});
		};
	
		return {
			btnClick:btnClick,
		};
}();