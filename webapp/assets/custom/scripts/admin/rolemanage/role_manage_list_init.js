define(['datatable_ajax'], function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "role_id",
				"bSortable" : false,
				"mRender" : function(data, type, full){
					if(full.role_edit_status==1){
						return '<input type="checkbox" name="roleId" value="'+ data +'">';
					}else{
						return '';
					}
					
				}},
				{
					"sTitle" : "角色名称",
					"mData" : "role_name"
				},{
					"sTitle" : "角色级别",
					"mData" : "grad_grade_name"
				},{
					"sTitle" : "描述",
					"mData" : "role_describe"
				},{
					"sTitle" : "启用状态",
					"mData" : "role_use_status",
					"mRender" : function(data, type, full){
						if(data === 0) {
							return '<span class="label label-danger"> 关闭 </span>';
						}
						else {
							return '<span class="label label-success"> 启用 </span>';
						}
					}
				},{
					"sTitle" : "操作",
					"mData" : "roleId",
					"bSortable" : false,
					"mRender" : function(data, type, full){
							return '<a href="admin/roleManage/toRoleManageAddOREdit?roleId='+full.role_id+'" class="btn btn-xs blue ajaxify">编辑及授权</a>';
					}
				}];
	
	var initDataTableAjax = function(){
		DataTableAjax.init("#adminRoleManageList", "admin/roleManage/getRoleManageList", aoData);
	};
    
    var initEvent = function() {
		$('#uploadExcelFileSubmit').on('click', function(e){
			FormFileUpload.ajaxSubmitModelFile("#uploadExcelFileFrom","#uploadExcelFileSubmit","uploadExcelFileInput");
		});
	  	
	};
	
	return {
		init : function(){
			initDataTableAjax();
			initEvent();
		}
	};
});