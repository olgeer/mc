define(['grade_list_init'], function(GradeListInit) {

	var initEvent = function() {
		$('#submitGradeInfo').on('click', function(e){
			e.preventDefault();
			if($("#gradeForm").valid()){
				Layout.ajaxSubmitModel("#gradeForm", "#submitGradeInfo",
				function(){
					$('#gradeInfo').modal('hide');
					dataTable=GradeListInit.getDataTableObject();
					dataTable.addAjaxParam("iTotalRows", '0');
					dataTable.getDataTable().fnDraw();
				});
	  		}
		});
	  	
	};
	 var handleValidation = function() {
            var form= $('#gradeForm');
  			var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                	gradGradeNo: {
                        required: true
                    },
                    gradGradeName: {
                        required: true
                    },
                    gradType: {
                        required: true
                    }
                },
                messages : {
                	gradGradeNo : {
					required : "请输入级别编号"
				},
				gradGradeName : {
					required : "请输入级别名称"
				},
				gradType : {
					required : "请选择级别名称"
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