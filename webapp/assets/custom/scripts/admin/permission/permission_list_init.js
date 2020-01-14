define(['datatable_ajax'], function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "perm_id",
				"mRender" : function(data, type, full){
					return '<input type="checkbox" name="perm_id" value="'+ full.perm_id +'">';
				}},
				{
					"sTitle" : "权限名称",
					"mData" : "perm_name"
				},{
					"sTitle" : "上级权限名称",
					"mData" : "perm_parent_name"
				},{
					"sTitle" : "权限URL",
					"mData" : "perm_url",
					"mRender" : function(data, type, full){
						if(data!=null && data!=""){
							if(data.length>20){
								return "<div title='"+data+"'>"+data.substring(0,20)+"..."+"</div>";
							}else{
								return "<div title='"+data+"'>"+data+"</div>";
							}
						}else{
							return "";
						}
					}
				},{
					"sTitle" : "权限",
					"mData" : "perm_resource"
				},{
					"sTitle" : "状态",
					"mData" : "perm_use_status",
					"mRender" : function(data, type, full){
						if(data === 0) {
							return '<span class="label label-danger"> 关闭 </span>';
						}
						else {
							return '<span class="label label-success"> 启用 </span>';
						}
					}
				},{
					"sTitle" : "类型",
					"mData" : "perm_type",
					"mRender" : function(data, type, full){
						if(data === 0) {
							return '<span class="label label-info">功能</span>';
						}
						else {
							return '<span class="label label-success">模块</span>';
						}
					}
				},{
					"sTitle" : "操作",
					"mData" : "perm_id",
					"bSortable" : false,
					"mRender" : function(data, type, full){
							return '<a permId="'+full.perm_id+'" class="btn btn-xs blue toModel">编辑</a>';
					}
				}];
	var dataTable;
	var initDataTableAjax = function(){
		dataTable=DataTableAjax.init("#adminPermissionList", "admin/permission/getPermissionDatatable", aoData);
	};
    
	var permissionModel =function(permId) {
		var $modal = $('#permissionModel');
		$('body').modalmanager('loading');
		if(typeof(permId)=="undefined"){
			$modal.load("admin/permission/toPermissionAddOREdit?"+Math.random(), '', function(){
				$modal.modal();
			});
		}else{
			$modal.load("admin/permission/toPermissionAddOREdit?"+Math.random()+"&permId="+permId, '', function(){
				$modal.modal();
			});
		
		}
    };
    
    
     var initEvent = function () {
    	//添加模块
    	$('#addPermission').on('click', function(e){
    		permissionModel();
    	});
    	//注册编辑事件
    	$("#adminPermissionList").on("click", '.toModel', function(){
    		var permId = $(this).attr('permId');
    		permissionModel(permId);
	 	});
    };
	
	return {
		init : function(){
			initDataTableAjax();
			initEvent();
		},
		getDataTableObject: function(){
			return  dataTable;
		}
	};
});