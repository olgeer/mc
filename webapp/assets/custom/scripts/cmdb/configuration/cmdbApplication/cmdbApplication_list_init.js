define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "应用名称",
			"mData" : "application_name"
		}, {
			"sTitle" : "应用类型",
			"mData" : "application_type"
		},{
			"sTitle" : "监听端口",
			"mData" : "monitor_port"
		},{
			"sTitle" : "服务部署路径",
			"mData" : "service_deploy_load"
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_application_edit_' + data + '" href="configuration/cmdb_application/' + data + '?displayName=应用"><i class="fa fa-pencil"></i> 编辑</a>');
				//params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_application_add_vip_' + data + '" href="configuration/application_with_vip/add?displayName=vip&application_id=' + data + '"><i class="fa fa-pencil"></i> 添加vip</a>');
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_application_view_' + data + '" href="configuration/cmdbApplication/view?id=' + data + '"><i class="fa fa-eye"></i>详情</a>');
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbApplication_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_application&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});