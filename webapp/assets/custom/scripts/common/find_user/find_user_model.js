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
	var initBaseEvent = function(btnId,showId,hiddenId) {
		
	   $('#department_multiple').select2({
		   placeholder: "请选择部门",
            allowClear: true,
            formatResult: formatResult,
            formatSelection: formatSelection
        });
	   
	   $.getJSON("common/queryUser/getDepartmentJSON", function (data) {
		   $('#department_multiple').empty();//清空下拉框
	        $.each(data, function (i, item) {
	        	$('#department_multiple').append("<option   value='" + item.Value + "'>&nbsp;" + item.Text + "</option>");
	        });
	    });
		// 查询按钮
		$("#findNameAccountBtn").on("click", function() {
			queryUserInfo();
		});
		
		// 键盘Enter事件
		$("#findNameAccountInput").keypress(function(e) {
			if (e.which == 13) {
				queryUserInfo();
				return false;
			}
		});
		
		$('#findUserList').on("dblclick", function() {
			$("#findUserList option:selected").each(function(){
				$("#"+showId).html($(this).text());
				$("#"+hiddenId).val($(this).val());
			});
			$("#findUserList option").remove();//清空
			$("#findUserModel").modal('hide');
		});
		
		$('#'+btnId).on("click", function() {
  			$("#findUserModel").modal();
  		});
		
		//确定事件
		$("#sureSelectedUserBtn").on('click', function(e) {
			debugger;
			$("#findUserList option:selected").each(function(){
				$("#"+showId).html($(this).text());
				$("#"+hiddenId).val($(this).val());
			});
			$("#findUserList option").remove();//清空
		});	
	}

	//异步获取用户信息
	var queryUserInfo = function() {
		var nameOrAccount = $("#findNameAccountInput").val();
		var departmentId = $("#department_multiple").val();
		$.post("common/queryUser/getUserInfo", {
			"nameOrAccount" : nameOrAccount,
			"departmentId":departmentId
		}, function(data) {
			if (null != data) {
				$('#findUserList').empty();
				for ( var o in data) {
					$('#findUserList').multiSelect(
							'addOption',
							{
								value : data[o].usba_id,
								text : data[o].usba_name + " [" + data[o].usba_account + "]",
								index : o
							});
				}
			}
		}, "json");
	};
	return {
		init:function(btnId,showId, hiddenId) {
			initBaseEvent(btnId,showId,hiddenId);
		}
		
	};
});