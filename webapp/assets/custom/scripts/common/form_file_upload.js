/**
 * 有带有附件上传的表单提交方法
 */
var FormFileUpload = function(){
	
	/**
	 * 一般表单提交
	 * inputIds为<input>中的Id,上传多个则使用Id数组
	 */
	ajaxSubmitFile = function(formId, submitId,inputIds) {
			submitFormId = formId;
			submitButtonId = submitId;
			submitInputIds = inputIds;
			
			Metronic.scrollTop();
			var url = $(submitId).attr("href");
			var pageContentBody = $('.page-content .page-content-body');
			
			Metronic.startPageLoading();
			
			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}
			$.ajaxFileUpload({
				type : "post",
				cache : false,
				url : url,
				dataType : "HTML",
				fileElementId:inputIds,   
				data : $(formId).serializeArray(),
				success : function(res) {
					
					Metronic.stopPageLoading();
					
					if(res.length < 200){
						var messages = res.split(',');
						//补交
						if(messages[0] == "delay") {
							var $modal = $('.ajaxSubmitDelayModal');
							
							if($modal.length == 0) {
								$('<div class="modal fade ajaxSubmitDelayModal" tabindex="-1" data-keyboard="false" data-width="960"></div>')
								.appendTo($('body'));
								$modal = $('.ajaxSubmitDelayModal');
							}
							// create the backdrop and wait for next modal to be triggered
							Metronic.stopPageLoading();				
							$modal.load('common/delayreason/toDelayReasonFileForm', '', function(){
								$modal.modal();
							});
							return;
						//超时不给补交咯
						} else if(messages[0] == "process_not_start") {
							var $modal = $('.ajaxifyNotStartModal');
							if($modal.length == 0) {
								$('<div class="modal fade ajaxifyNotStartModal" tabindex="-1" data-keyboard="false" data-width="500"><div class="modal-body"><p>'+messages[1]+'未开始</p></div><div class="modal-footer"><a class="btn blue" data-dismiss="modal">确定</a></div></div>')
								.appendTo($('body'));
								$modal = $('.ajaxifyNotStartModal');
							}
							Metronic.stopPageLoading();
							$modal.modal();
							return;
						} else if(messages[0] == "process_end") {
							var $modal = $('.ajaxifyProcessEndModal');
							if($modal.length == 0) {
								$('<div class="modal fade ajaxifyProcessEndModal" tabindex="-1" data-keyboard="false" data-width="500"><div class="modal-body"><p>'+messages[1]+'已结束</p></div><div class="modal-footer"><a class="btn blue" data-dismiss="modal">确定</a></div></div>')
								.appendTo($('body'));
								$modal = $('.ajaxifyProcessEndModal');
							}
							Metronic.stopPageLoading();
							$modal.modal();
							return;
						} else if(messages[0] == "process_disable") {
							var $modal = $('.ajaxifyProcessDisableModal');
							if($modal.length == 0) {
								$('<div class="modal fade ajaxifyProcessDisableModal" tabindex="-1" data-keyboard="false" data-width="500"><div class="modal-body"><p>'+messages[1]+'不可用</p></div><div class="modal-footer"><a class="btn blue" data-dismiss="modal">确定</a></div></div>')
								.appendTo($('body'));
								$modal = $('.ajaxifyProcessDisableModal');
							}
							Metronic.stopPageLoading();	
							$modal.modal();
							return;
						}
					}
					
					if (res.length < 200) {
                    	Metronic.alert({
                    		type: 'danger', 
                    		icon: 'warning', 
                    		message: res, 
                    		container: $(formId), 
                    		place: 'prepend' 
                    	});
                    } else {
						pageContentBody.html(res);
						Layout.fixContentHeight(); // fix content height
						Metronic.initAjax(); // initialize core stuff
                    };
				},
				error : function(res) {
					
					Metronic.stopPageLoading();
					
					Metronic.alert({
                		type: 'danger', 
                		icon: 'warning', 
                		message: '网络错误...', 
                		container: $(formId), 
                		place: 'prepend'
                	});
				}
			});
		};
		
		/**
		 * Model中的表单提交
		 * inputIds为<input>中的Id,上传多个则使用Id数组
		 * callback为提交成功后回调函数
		 */
		ajaxSubmitModelFile = function(formId, submitId,inputIds,callback) {
		
			var url = $(submitId).attr("href");
			
			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}

			$.ajaxFileUpload({
				type : "post",
				cache : false,
				url : url,
				fileElementId:inputIds,   
				data : $(formId).serializeArray(),
				success : function(res) {
				
					if(res=='success'){
						Metronic.scrollTop();
						callback();
					}else{
						Metronic.alert({
                		type: 'danger', 
                		icon: 'warning', 
                		message: res, 
                		container: $(formId), 
                		place: 'prepend'
                	});
					}
				},
				error : function() {
					
					Metronic.alert({
                		type: 'danger', 
                		icon: 'warning', 
                		message: '网络错误...', 
                		container: $(formId), 
                		place: 'prepend'
                	});
				}
			});
		};
		
		/**
		 * 用户头像上传
		 * inputIds为<input>中的Id,上传多个则使用Id数组
		 */
		ajaxSubmitAvatar = function(formId, submitId,inputIds,imgId) {
			
				Metronic.scrollTop();
				var url = $(submitId).attr("href");
				var pageContentBody = $('.page-content .page-content-body');
				
				Metronic.startPageLoading();
				
				if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
					$('.navbar-toggle').click();
				}
				$.ajaxFileUpload({
					type : "post",
					cache : false,
					url : url,
					dataType : "HTML",
					fileElementId:inputIds,   
					data : $(formId).serializeArray(),
					success : function(res) {
						
						Metronic.stopPageLoading();
						var imgSrc = $(imgId).attr('src')+"?";
						$(imgId).attr("src",imgSrc+ Math.random());
						//销毁filestyle，然后重新创建
						$(":file").filestyle('destroy');
						$(":file").filestyle({buttonText: "选择"});
					},
					error : function(res) {
						
						Metronic.stopPageLoading();
						
						Metronic.alert({
	                		type: 'danger', 
	                		icon: 'warning', 
	                		message: '网络错误...', 
	                		container: $(formId), 
	                		place: 'prepend'
	                	});
					}
				});
			};
			
			//初始化补交modal确定的事件
			var submitFormId = null;
			var submitButtonId = null;
			var submitInputIds = null;
			var isInit = false;
			var initEvent = function() {
				if(!isInit) {
					$("body").on('click', '#delayFileFormSubmit', function() {
						delayReason = $("#delayReason").val();
						$("<input>", {
							  type: "hidden",
							  name: "delayReason",
							  val: delayReason
						}).appendTo(submitFormId);
						FormFileUpload.ajaxSubmitFile(submitFormId, submitButtonId,submitInputIds);
						$('.ajaxSubmitDelayModal').modal('hide');
					});
					isInit = true;
				}
			};
			
		return {
			init : function(){
				initEvent();
			},
			ajaxSubmitModelFile:ajaxSubmitModelFile,
			ajaxSubmitFile:ajaxSubmitFile,
			ajaxSubmitAvatar:ajaxSubmitAvatar
		};
}();