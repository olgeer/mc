define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "服务具体名称",
			"mData" : "cs.definite_instance_name"
		}, {
			"sTitle" : "主机名",
			"mData" : "ch.host_name"
		}, {
			"sTitle" : "部署路径",
			"mData" : "csh.service_deploy_load"
		},{
			"sTitle" : "监听端口",
			"mData" : "csh.monitor_port"
		},{
			"sTitle" : "服务运行状态",
			"mData" : "csh.service_status"
		},{
			"sTitle" : "操作",
			"mData" : "csh.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_service_host_edit_' + data + '" href="configuration/cmdb_service_host/' + data + '?displayName=服务部署"><i class="fa fa-pencil"></i> 编辑</a>');
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbServiceHost_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_service cs,cmdb_service_host csh,cmdb_host ch&_joincols=cs.id csh.belong_to_service_id(-),csh.belong_to_host_id(-) ch.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});