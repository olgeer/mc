define([],function() {
   /* var	closeTab=function() {
		Layout.closeTab();
	};*/

	 
	/**注册修改邮箱跟电话点击事件*/
	var toUpdateMailAndPhone = function(){
		$('#toUpdateMailAndPhoneSubmit').on('click', function(e){
			e.preventDefault();
			
			if(!$("#toUpdateMailAndPhone").valid()){
	     		return;
	     	}
			
			Metronic.startPageLoading();
			
			$.ajax({
				   type: "POST",
				   url: "admin/manageUserAccount/toUpdateMailAndPhone",
				   data: $('#toUpdateMailAndPhone').serialize(),
				   success: function(res) {
					   	Metronic.stopPageLoading();
						if (res.sStatus == true) {
	                   	Metronic.alert({
	                   		type: 'success', 
	                   		icon: 'check', 
	                   		message: res.sMessage, 
	                   		container: $('#toUpdateMailAndPhone'), 
	                   		place: 'prepend'
	                   	});
	                   }else if (res.sStatus == false) {
	                   	Metronic.alert({
	                   		type: 'danger', 
	                   		icon: 'warning', 
	                   		message: res.sMessage, 
	                   		container: $('#toUpdateMailAndPhone'), 
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
		            		container: $('#toUpdateMailAndPhone'), 
		            		place: 'prepend'
		            	});
					}
				
				});
		});
	};
	 
//	var editUserAccountSubmit = function(){
//		$("#editUserAccountSubmit").on('click', function(e) {
//	     	e.preventDefault();
//	     	if($("#editUserAccountForm").valid()){
//	     		Layout.ajaxSubmit("#editUserAccountForm", "#editUserAccountSubmit");
//	     	}
//	     });
//	};
		
	return {
		init : function(){
			toUpdateMailAndPhone();
			//closeTab();

		}
	};
});