define(['datatable_ajax'], function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "usro_id",
				"bSortable" : false,
				"mRender" : function(data, type, full){
					return '<input type="checkbox" name="usro_id" value="'+ data +'">';
				}},
				{
					"sTitle" : "部门名称",
					"mData" : "depa_name"
				},{
					"sTitle" : "用户账号",
					"mData" : "usba_account"
				},{
					"sTitle" : "用户名称",
					"mData" : "usba_name"
				},{
					"sTitle" : "角色名称",
					"mData" : "role_name"
				},{
					"sTitle" : "启用状态",
					"mData" : "usro_status",
					"mRender" : function(data, type, full){
						if(data === 1) {
							return '<span class="label label-danger"> 禁用 </span>';
						}else if(data === 2) {
							return '<span class="label label-success"> 启用 </span>';
						}
					}
				},{
					"sTitle" : "操作",
					"mData" : "usba_id",
					"bSortable" : false,
					"mRender" : function(data, type, full){
						return '--';
					}
				}];
	var dataTable;
	var initDataTableAjax = function(){
		dataTable=DataTableAjax.init("#adminUserRoleList", "admin/userRole/getUserRoleDatatable", aoData);
	};
    
    var initEvent = function() {
    	
    	$('#submitSelectedUser').on('click', function(e){
			e.preventDefault();
			debugger;
			if($("#user_role_role").select2("data")==null){
				Metronic.alert({
	         		type: 'danger', 
	         		icon: 'warning', 
	         		message: "请选择需要绑定的角色", 
	         		container: $("#userRoleForm"), 
	         		place: 'prepend'
	             });
				return;
			}
			var userIdList = new Array();
			$("#user_role_selectedUser option").each(function(){
					userIdList.push($(this).val());
			});
			
			if(userIdList.length==0){
				Metronic.alert({
	         		type: 'danger', 
	         		icon: 'warning', 
	         		message: "请选择需要绑定的用户", 
	         		container: $("#userRoleForm"), 
	         		place: 'prepend'
	             });
				return;
			}else{
				$('#user_role_usba_ids').val(userIdList);
			}
			
			Layout.ajaxSubmitModel("#userRoleForm", "#submitSelectedUser",
			function(){
				$('#userRoleQueryUserModel').modal('hide');
				dataTable.addAjaxParam("iTotalRows", '0');
				dataTable.getDataTable().fnDraw();
			});
		});
    	
    	$('#addUserRole').on("click", function() {
  			$("#userRoleQueryUserModel").modal();
  			$("#user_role_notSelectedUser option").remove();
  			$("#user_role_selectedUser option").remove();
  			$("#user_role_queryNameAccountInput").val("");
  		});
    	
    	 $('#user_role_department').select2({
  		   placeholder: "请选择部门",
              allowClear: true,
              formatResult: formatResult,
              formatSelection: formatSelection
          });
    	 $('#user_role_role').select2({
    		 placeholder: "请选择角色",
    		 allowClear: true
    	 });
  	   
  	   $.getJSON("common/queryUser/getDepartmentJSON", function (data) {
  		   $('#user_role_department').empty();//清空下拉框
  	        $.each(data, function (i, item) {
  	        	$('#user_role_department').append("<option   value='" + item.Value + "'>&nbsp;" + item.Text + "</option>");
  	        });
  	    });
  	   
  	   $.getJSON("admin/userRole/getRoleJSON", function (data) {
  		   $('#user_role_role').empty();//清空下拉框
  		   $.each(data, function (i, item) {
  			   $('#user_role_role').append("<option   value='" + item.role_id + "'>&nbsp;" + item.role_name + "</option>");
  		   });
  	   });
  		// 查询按钮
  		$("#user_role_queryNameAccountBtn").on("click", function() {
  			queryUserInfo();
  		});
  		
  		// 键盘Enter事件
  		$("#user_role_queryNameAccountInput").keypress(function(e) {
  			if (e.which == 13) {
  				queryUserInfo();
  				return false;
  			}
  		});
  		
  		//添加用户按钮
  		$('#user_role_addUser').on("click", function() {
  			var selectedUser = $('#user_role_selectedUser');
  			$("#user_role_notSelectedUser option:selected").each(function(){
  				selectedUser.multiSelect('addOption', { 
  					value: $(this).val(), 
  					text: $(this).text()
  				});
  			});
  			$("#user_role_notSelectedUser option:selected").remove();
  		});
  		
  		//删除用户按钮
  		$('#user_role_removeUser').on("click", function() {
  			var notSelectedUser = $('#user_role_notSelectedUser');
  			$("#user_role_selectedUser option:selected").each(function(){
  				notSelectedUser.multiSelect('addOption', { 
  					value: $(this).val(), 
  					text: $(this).text()
  				});
  			});
  			$("#user_role_selectedUser option:selected").remove();
  		});
  		
  		//双击左边Select项
  		$('#user_role_notSelectedUser').on("dblclick", function() {
  			var selectedUser = $('#user_role_selectedUser');
  			$("#user_role_notSelectedUser option:selected").each(function(){
  				selectedUser.multiSelect('addOption', { 
  					value: $(this).val(), 
  					text: $(this).text()
  				});
  			});
  			$("#user_role_notSelectedUser option:selected").remove();
  		});
  		
  		//双击右边Select项
  		$('#user_role_selectedUser').on("dblclick", function() {
  			var notSelectedUser = $('#user_role_notSelectedUser');
  			$("#user_role_selectedUser option:selected").each(function(){
  				notSelectedUser.multiSelect('addOption', { 
  					value: $(this).val(), 
  					text: $(this).text()
  				});
  			});
  			$("#user_role_selectedUser option:selected").remove();
  		});
    };
    
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
	
	//异步获取用户信息
	var queryUserInfo = function() {
		var nameOrAccount = $("#user_role_queryNameAccountInput").val();
		var departmentId = $("#user_role_department").val();
		$.post("common/queryUser/getUserInfo", {
			"nameOrAccount" : nameOrAccount,
			"departmentId":departmentId
		}, function(data) {
			if (null != data) {
				$('#user_role_notSelectedUser').empty();
				for ( var o in data) {
					$('#user_role_notSelectedUser').multiSelect(
							'addOption',
							{
								value : data[o].usba_id,
								text : data[o].usba_name + "(" + data[o].usba_account + ")",
								index : o
							});
				}
			}
		}, "json");
	};
	
	return {
		init : function(){
			initDataTableAjax();
			initEvent();
		}
	};
});