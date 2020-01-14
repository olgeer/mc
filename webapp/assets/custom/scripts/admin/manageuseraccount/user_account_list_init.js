define(['datatable_ajax'], 
		function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "usba_id",
				"bSortable" : false,
				"mRender" : function(data, type, full){
					return '<input type="checkbox" name="usba_id" value="'+ data +'">';
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
					"sTitle" : "直属上级",
					"mData" : "superior_name"
				},{
					"sTitle" : "启用状态",
					"mData" : "usba_account_enable",
					"mRender" : function(data, type, full){
						if(data === 0) {
							return '<span class="label label-danger"> 禁用 </span>';
						}
						else {
							return '<span class="label label-success"> 启用 </span>';
						}
					}
				},{
					"sTitle" : "锁定状态",
					"mData" : "usba_account_locked",
					"mRender" : function(data, type, full){
						if(data === 0) {
							return '<span class="label label-danger">锁定 </span>';
						}
						else {
							return '<span class="label label-success">开放 </span>';
						}
					}
				},{
					"sTitle" : "操作",
					"mData" : "usba_id",
					"bSortable" : false,
					"mRender" : function(data, type, full){
						return '<a href="admin/manageUserAccount/toEditUserAccount/'+data+'" module_id="toEditUserAccountEdit" class="btn btn-xs blue ajaxify">编辑</a>';
					}
				}];
	var dataTable;
	var initDataTableAjax = function(){
		dataTable=DataTableAjax.init("#adminUserAccountList", "admin/manageUserAccount/getUserAccountList", aoData);
	};
    
	
	/** 启用所有的用户账号 */
	var activateAllUserAccountBtn = function(){
		$('#activateAllUserAccountBtn').on('click', function(e){
			e.preventDefault();
			dataTable.addAjaxParam("iTotalRows", '0');
			dataTable.getDataTable().fnDraw();
		});
	};
	
	
	return {
		init : function(){
			initDataTableAjax();
			activateAllUserAccountBtn();
		},
		getDataTableObject: function(){
			return  dataTable;
		}
	};
});