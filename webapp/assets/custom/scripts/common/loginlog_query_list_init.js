define(['datatable_ajax'], 
		function(DataTableAjax) {

	var aoData = [ {
		"sTitle" : "登录账号",
		"mData" : "loloUsroName",
		"bSortable" : false
	}, {
		"sTitle" : "登录正常/异常",
		"mData" : "loloType",
		"bSortable" : false,
		"mRender" : function(data,type,full){
			if(data === 0){
				return '<span class="label label-success"> 正常</span>';
			}else{
				return '<span class="label label-danger"> 异常 </span>';
			}
			
		}
	}, {
		"sTitle" : "异常描述",
		"mData" : "loloExceptionDetail",
		"bSortable" : false
	}, {
		"sTitle" : "角色",
		"mData" : "loloRoleName",
		"bSortable" : false
	}, {
		"sTitle" : "登录IP",
		"mData" : "loloIp",
		"bSortable" : false
	}, {
		"sTitle" : "登录时间",
		"mData" : "loloLoginDate",
		"bSortable" : true
	}, {
		"sTitle" : "退出时间",
		"mData" : "loloLogoutDate",
		"bSortable" : true
	}, {
		"sTitle" : "操作",
		"mData" : "id",
		"bSortable" : false,
		"mRender" : function(data,type,full){
			return '';
		}
	}];
	
	var dataTable;
	var initDataTableAjax = function(){
		dataTable=DataTableAjax.init("#loginlogQueryList", "common/log/getLoginLogDatatable?loloOwn="+$("#loloOwn").val(), aoData);
		
	};
	
	
	return {
		init : function(){
			initDataTableAjax();
		},
		getDataTableObject: function(){
			return  dataTable;
		}
	};
});