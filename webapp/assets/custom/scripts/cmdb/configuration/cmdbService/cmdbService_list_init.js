define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "应用名称",
			"mData" : "ca.application_name",
			"mRender" : function(data,type,full){
				var str = [];
				full.ca.application_name && str.push(full.ca.application_name);
				full.ca.application_type && str.push(full.ca.application_type);
				return str.join('-');
			}
		}, {
			"sTitle" : "主机名",
			"mData" : "ch.host_name"
		}, {
			"sTitle" : "服务名称",
			"mData" : "cs.definite_instance_name"
		},{
			"sTitle" : "监听端口",
			"mData" : "cs.monitor_port"
		},{
			"sTitle" : "服务部署路径",
			"mData" : "cs.service_deploy_load"
		},{
			"sTitle" : "服务账号",
			"mData" : "csac.service_account"
		},{
			"sTitle" : "操作",
			"mData" : "cs.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_service_edit_' + data + '" href="configuration/cmdb_service/' + full.cs.id + '?displayName=服务"><i class="fa fa-pencil"></i>服务</a>');
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbService_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_service cs,cmdb_application ca,cmdb_service_account_code csac ,cmdb_service_host csh,cmdb_host ch&_joincols=cs.belong_to_application_id(-) ca.id,cs.belong_to_sa_id(-) csac.id,cs.id csh.belong_to_service_id,csh.belong_to_host_id ch.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});