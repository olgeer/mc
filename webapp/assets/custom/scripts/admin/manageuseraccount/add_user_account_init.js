define(['../../common/find_user/find_user_model.js'],function(findUserModel) {	
	var handleValidation = function() {
        var form= $('#addNewUserAccountForm');
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            rules: {
	            	usbaAccount :{
							required: true,
							minlength: 6,
							maxlength: 30
	            	},
	            	usbaName : {
							required: true,
							minlength: 1,
							maxlength:30
	            	},
	            	depaId : {
						required: true
		            }
            },
            messages : {
            	usbaAccount : {
					required : "请输入用户帐号"
				},
				usbaName : {
					required : "请输入用户姓名"
				},
				depaId : {
					required: "请选择部门"
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
	 
	 var addNewUserAccountSubmit = function(){
		$("#addNewUserAccountSubmit").on('click', function(e) {
	     	e.preventDefault();
	     	if($("#addNewUserAccountForm").valid()){
	     		Layout.ajaxSubmit("#addNewUserAccountForm", "#addNewUserAccountSubmit");
	     	}
	     });
	};
	
	/**
	 * 初始化select2插件
	 */
	function ininSelect2() {
		var select = $("#user-add-role-ids, #user-add-position-ids").select2({
			placeholder:' 请选择 ',
		});
	}
	
	function initEvent(){
		findUserModel.init('usba_superior_name_btn','usba_superior_name','usbaSuperiorId');
	}
	return { 
		init : function(){
			addNewUserAccountSubmit();
			handleValidation();
			ininSelect2();
			initEvent();
		}
	};
});