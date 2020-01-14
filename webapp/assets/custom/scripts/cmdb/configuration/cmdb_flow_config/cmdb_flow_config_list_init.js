define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [{
			"sTitle" : "配置描述",
			"mData" : "process_def_id"
		},
		{
			"sTitle" : "配置",
			"mData" : "config",
			"mRender" : function(data,type,full){
				return '<pre>' + data + '</pre>';
			}
		},{
			"sTitle" : "操作",
			"mData" : "process_def_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_device_brand_edit_' + data + '" href="configuration/bpm_flow_cmdb_config/' + data + '?displayName=设备品牌"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=bpm_flow_cmdb_config&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});