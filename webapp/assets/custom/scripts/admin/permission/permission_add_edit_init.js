define(['../../admin/permission/permission_list_init.js'], function(permissionListInit) {
	

	var initEvent = function() {
		$('#submitPermission').on('click', function(e){
			e.preventDefault();
			if($("#permissionForm").valid()){
				Layout.ajaxSubmitModel("#permissionForm", "#submitPermission",
				function(){
				$('#permissionModel').modal('hide');
					dataTable=permissionListInit.getDataTableObject();
					dataTable.addAjaxParam("iTotalRows", '0');
					dataTable.getDataTable().fnDraw();
				});
	  		}
		});
	  	
	};
	 var handleValidation = function() {
            var form= $('#permissionForm');
  			var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                	permName: {
                        minlength: 2,
                        required: true
                    },
                    permResource: {
                    	required: true
                    },
                    permSort: {
                    	required: true,
                    	maxlength: 2,
                    	digits: true
                    }
                },
                messages : {
                	permName : {
						required : "请输入权限名称"
					},
					permResource : {
						required : "请输入权限资源名称"
					},
					permSort : {
						required : "请输入数字"
					}
				},
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
	 
	return {
		init : function(){
			initEvent();
			handleValidation();
		}
	};
});