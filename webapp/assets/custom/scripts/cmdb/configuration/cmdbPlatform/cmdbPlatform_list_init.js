define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "平台名称",
			"mData" : "cp.platform_name"
		}, {
				"sTitle" : "项目名称",
				"mData" : "ccp.chief_platform_name"
			},{
			"sTitle" : "公司",
			"mData" : "cp.company"
		}, {
			"sTitle" : "管理员",
			"mData" : "cp.manager"
		}, {
			"sTitle" : "管理员电话",
			"mData" : "cp.manager_tel"
		},{
			"sTitle" : "研发人",
			"mData" : "cp.developer"
		},{
			"sTitle" : "研发人电话",
			"mData" : "cp.developer_tel"
		},{
			"sTitle" : "操作",
			"mData" : "cp.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_chiefplatform_edit_' + data + '" href="configuration/cmdb_platform/' + data + '?displayName=平台"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbPlatform_view_' + data + '" href="configuration/cmdbPlatform/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbPlatform_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_platform cp,cmdb_chief_platform ccp&_joincols=cp.belongs_to_chief_id ccp.id&_action=get", getAoData(params));
	};


	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});