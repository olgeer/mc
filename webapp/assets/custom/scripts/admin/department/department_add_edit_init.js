define(['department_list_init'], function(DepartmentListInit) {
	
	//注册Select监听事件
	var listenGradeSelect=function(){
		//获取gradeSelect option value,value中包含需要级联select的id 
		var arr = new Array();
		$('#gradeSelect option').each(function () {
             var val = $(this).val().split("%")[1]; //获取单个value
             arr.push(val);
         });
         //级联绑定
         for(var i=0;i<arr.length;i++){
         	if(arr.length>i+2){
	        	 $("#gradeGradeNo"+arr[i+1]).chained("#gradeGradeNo"+arr[i]);
        	}
		$('#gradeSelect').change(function(){ 
			//获取级别ID
			var val=$(this).children('option:selected').val();
			var gradeGradeNo=val.split("%")[1];
			//校级才开始显示部门select
			for (var i=1;i<arr.length;i++)
			{
				if(i<gradeGradeNo){
					$('#gradeGradeNoDiv'+i).show();
				}else{
					$('#gradeGradeNoDiv'+i).hide();
				}
			}
			});
		};
	};
	
	var initEvent = function() {
		$('#submitDepartmentInfo').on('click', function(e){
			e.preventDefault();
			if($("#departmentForm").valid()){
				Layout.ajaxSubmitModel("#departmentForm", "#submitDepartmentInfo",
				function(){
					$('#departmentInfo').modal('hide');
					dataTable=DepartmentListInit.getDataTableObject();
					dataTable.addAjaxParam("iTotalRows", '0');
					dataTable.getDataTable().fnDraw();
				});
	  		}
		});
	  	
	};
	 var handleValidation = function() {
            var form= $('#departmentForm');
  			var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                    depaNo: {
                        required: true
                    },
                    depaName: {
                        required: true
                    }
                },
                messages : {
				depaNo : {
					required : "请输入部门编号"
				},
				depaName : {
					required : "请输入部门名称"
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
			listenGradeSelect();
			handleValidation();
			initEvent();
		}
	};
});