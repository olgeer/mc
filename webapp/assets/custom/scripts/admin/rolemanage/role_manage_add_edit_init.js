define(function() {

	var handleValidation = function() {
        var form= $('#roleMangeForm');
		var error = $('.alert-danger', form);
        var success = $('.alert-success', form);
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            rules: {
            	roleName: {
                    required: true
                },
                roleType: {
                    required: true
                }
            },
            messages : {
	            roleName : {
					required : "请输入角色名称"
				},
				roleType : {
					required : "请选择角色类别"
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
 
    var initEvent = function() {
		$('#saveRoleMange').on('click',function(e){
			e.preventDefault();
			if($("#roleMangeForm").valid()){
				Layout.ajaxSubmit("#roleMangeForm","#saveRoleMange");
			}
			
		});
		
		
		$('#tree_2').on("changed.jstree", function (e, data) {
        	$('#treeIds').val(data.selected);
	    }).jstree({
            'plugins': ["themes", "checkbox", "types"],
            'core': {
                "themes" : {
                    "responsive": false
                }, 
                'data':{
                    'url' : function (node) {
                    	var url = 'admin/roleManage/getTreeJson';
                    	var	roleId =$("#roleId").val();
                    	if(roleId!=null && roleId!="" && roleId!=undefined){
                    		url += '?roleId='+roleId;
                    	}
                    	return url;
                     },
                     'data' : function (node) {
                    	
                     }
                } 
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
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