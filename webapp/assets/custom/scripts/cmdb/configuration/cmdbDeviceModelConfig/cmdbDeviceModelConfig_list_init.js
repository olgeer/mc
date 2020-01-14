define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "配件类型",
			"mData" : "config_model"
		}, {
			"sTitle" : "配件名称",
			"mData" : "config_name"
		}, {
			"sTitle" : "配件规格",
			"mData" : "config_specification"
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				
			return '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbLicence_view_' + data + '" href="configuration/cmdbLicence/view?licence_id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbDeviceModelConfig_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_device_model_config&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});