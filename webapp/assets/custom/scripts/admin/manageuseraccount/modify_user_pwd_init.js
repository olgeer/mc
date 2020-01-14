define([],function() {	
	var handleValidation = function() {
        var form= $('#modifyUserPwd');
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            rules: {
            	oldPwd :{ 
            		required: true,
            		rangelength: [6, 15],
            		remote: {
                        url: "admin/manageUserAccount/checkUserAccountPassword",
                        type: "post",
                        dataType: "json",
                        data:{
                        	oldPwd: function () {
                                return $("#oldPwd").val();
                            },
                            usbaId: function () {
                            	return $("#usbaId").val();
                            }
                        },
                        dataFilter: function (data) {
                            return data;
                        }
                    }
            	},
            	newPwd :{
            		required: true,
            		minlength:6,
            		maxlength:15
            	},
            	confirmNewPwd: {
                    required: true,
                    minlength:6,
            		maxlength:15,
                    equalTo: "#newPwd"
                  },
            },
            messages : {
            	oldPwd : {
					required: "请输入原密码",
					remote: "原密码不正确，请重新输入..."
	            },
            	newPwd : {
					required : "请输入新密码"
				},
	            confirmNewPwd : {
	            	required: "请输入确认新密码",
	            	equalTo: '请两次输入一样的密码'
	            }
			},
			// display error alert on form submit
			invalidHandler : function(event, validator) {
				var mg = '填写信息不完整或有误，请重新检查！';
     			 for(var obj in validator.errorMap)
 				 {
     				mg=validator.errorMap[obj];
     				break;
 				 }
     			 Metronic.alert({
	         		type: 'danger', 
	         		icon: 'warning', 
	         		message: mg, 
	         		container: form, 
	         		place: 'prepend'
	             });
			},
			
			// render error placement for each input type
			errorPlacement : function(error, element) {
				var icon = $(element).parent(".input-icon")
						.children("i");
				icon.removeClass("fa-check").addClass("fa-warning");
				icon.attr("data-original-title", error.text()).tooltip(
					{
						"container" : "body"
					});
			},
			
			// hightlight error inputs
			highlight : function(element) {
				// set error class to the control group
				$(element).closest(".form-group") .removeClass("has-success") .addClass("has-error");
			},

			success : function(label, element) {
				var icon = $(element).parent(".input-icon") .children("i");
				$(element).closest(".form-group") .removeClass("has-error") .addClass("has-success");
				icon.removeClass("fa-warning").addClass("fa-check");
			}
    	 });
	 };
	 
	/**修改密码.*/
	var updateUserAccountPassword = function(){
		$('#updateUserPasswordSubmit').on('click', function(e){
			e.preventDefault();
			
			if(!$("#modifyUserPwd").valid()){
	     		return;
	     	}
			
			Metronic.startPageLoading();
			
			$.ajax({
				   type: "POST",
				   url: "admin/manageUserAccount/updateUserAccountPassword",
				   data: $('#modifyUserPwd').serialize(),
				   success: function(res) {
					   	Metronic.stopPageLoading();
						if (res.sStatus == true) {
	                   	Metronic.alert({
	                   		type: 'success', 
	                   		icon: 'check', 
	                   		message: res.sMessage, 
	                   		container: $('#modifyUserPwd'), 
	                   		place: 'prepend'
	                   	});
	                   }else if (res.sStatus == false) {
	                   	Metronic.alert({
	                   		type: 'danger', 
	                   		icon: 'warning', 
	                   		message: res.sMessage, 
	                   		container: $('#modifyUserPwd'), 
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
		            		container: $('#modifyUserPwd'), 
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
			updateUserAccountPassword();
//			editUserAccountSubmit();
			handleValidation();
		}
	};
});