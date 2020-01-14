define(function() {
	
	//部门未选中时的格式
	 function formatResult(state) {
         if (!state.id){
        	 return state.text; 
         }else{
        	 return "<divc class='fa fa-users' />&nbsp;&nbsp;" + state.text;
         }
     }
	//部门被选中时的格式
	function formatSelection(state) {
	     if (!state.id){
	    	 return state.text; 
	     }else{
	    	 return "<divc class='fa fa-users' />&nbsp;&nbsp;" + state.text.split(' ')[1];
	     }
	}
	var initBaseEvent = function() {
		
	   $('#department').select2({
		   placeholder: "请选择部门",
            allowClear: true,
            formatResult: formatResult,
            formatSelection: formatSelection
        });
	   
	   $.getJSON("common/queryUser/getDepartmentJSON", function (data) {
		   $('#department').empty();//清空下拉框
	        $.each(data, function (i, item) {
	        	$('#department').append("<option   value='" + item.Value + "'>&nbsp;" + item.Text + "</option>");
	        });
	    });
		// 查询按钮
		$("#queryNameAccountBtn").on("click", function() {
			queryUserInfo();
		});
		
		// 键盘Enter事件
		$("#queryNameAccountInput").keypress(function(e) {
			if (e.which == 13) {
				queryUserInfo();
				return false;
			}
		});
		
		//添加用户按钮
		$('#addUser').on("click", function() {
			var selectedUser = $('#selectedUser');
			$("#notSelectedUser option:selected").each(function(){
				selectedUser.multiSelect('addOption', { 
					value: $(this).val(), 
					text: $(this).text()
				});
			});
			$("#notSelectedUser option:selected").remove();
		});
		
		//删除用户按钮
		$('#removeUser').on("click", function() {
			var notSelectedUser = $('#notSelectedUser');
			$("#selectedUser option:selected").each(function(){
				notSelectedUser.multiSelect('addOption', { 
					value: $(this).val(), 
					text: $(this).text()
				});
			});
			$("#selectedUser option:selected").remove();
		});
		
		//双击左边Select项
		$('#notSelectedUser').on("dblclick", function() {
			var selectedUser = $('#selectedUser');
			$("#notSelectedUser option:selected").each(function(){
				selectedUser.multiSelect('addOption', { 
					value: $(this).val(), 
					text: $(this).text()
				});
			});
			$("#notSelectedUser option:selected").remove();
		});
		
		//双击右边Select项
		$('#selectedUser').on("dblclick", function() {
			var notSelectedUser = $('#notSelectedUser');
			$("#selectedUser option:selected").each(function(){
				notSelectedUser.multiSelect('addOption', { 
					value: $(this).val(), 
					text: $(this).text()
				});
			});
			$("#selectedUser option:selected").remove();
		});
	}

		
	//异步获取用户信息
	var queryUserInfo = function() {
		var nameOrAccount = $("#queryNameAccountInput").val();
		var departmentId = $("#department").val();
		$.post("common/queryUser/getUserInfo", {
			"nameOrAccount" : nameOrAccount,
			"departmentId":departmentId
		}, function(data) {
			if (null != data) {
				$('#notSelectedUser').empty();
				for ( var o in data) {
					$('#notSelectedUser').multiSelect(
							'addOption',
							{
								value : +data[o].usro_id + "&"
										+ data[o].usba_name + "(" + data[o].usba_account + ")"+"[" + data[o].role_name + "]",
								text : data[o].usba_name + "(" + data[o].usba_account + ")"+"[" + data[o].role_name + "]",
								index : o
							});
				}
			}
		}, "json");
	};
	
	//绑定确定按钮与Tag之间操作事件
	var addSureSelectedUserEvent = function(tagElementObject, hiddenIdElementObject){
		//确定事件
		$("#sureSelectedUser").one('click', function(e) {
			var userIdList = new Array();
			$("#selectedUser option").each(function(){
				if ((tagElementObject.val()).indexOf($(this).text())<0){
					tagElementObject.addTag($(this).text());
					userIdList.push($(this).val());
				}
			});
			var userIds = hiddenIdElementObject.val();//获取原有的ID
			if('' != userIds && null != userIds){//原有ID为空
				hiddenIdElementObject.val(userIds+","+userIdList.join(","));	//拼接用户ID
			}
			else{
				hiddenIdElementObject.val(userIdList.join(","));
			}
			$("#selectedUser option").remove();//清空
			$("#notSelectedUser option").remove();//清空
		});	
	}
	//初始化tag控件 tagId：tag标签中的ID，hiddenId:用于保存用户ID的隐藏标签ID
	var initTag=function(btnId,tagId, hiddenId){
		 var tagElementObject;
		 var hiddenIdElementObject = $('#'+hiddenId);//存放收件的用户Id.
         $("input[name='"+tagId+"']").each(function () {
        	 tagElementObject = $(this);
         });
         tagElementObject.tagsInput({
 			'width':'100%',
 			'defaultText':'',
 			'interactive':false,
 			'onRemoveTag':function(tag){	//删除tag的回调函数.
 				var userIds = hiddenIdElementObject.val();
 				var userIdArray = userIds.split(",");
 				for(var o in userIdArray){
 					if(userIdArray[o].indexOf(tag) >= 0){
 						userIdArray.splice(o,1);
 					}
 				}
 				userIds = userIdArray.join(",");
 				hiddenIdElementObject.val(userIds);
             }
 		 });
         
       //点击主送人按钮触发事件
  		$('#'+btnId).on("click", function() {
  			addSureSelectedUserEvent(tagElementObject,hiddenIdElementObject);
  			$("#queryUserModel").modal();
  		});
	}
	return {
		init:function(btnId,tagId, hiddenId) {
			initBaseEvent();
			initTag(btnId,tagId, hiddenId);
		}
		
	};
});