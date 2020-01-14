define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "许可证名称",
			"mData" : "licence_name"
		}, {
			"sTitle" : "许可证用途",
			"mData" : "equipment_use"
		}, {
			"sTitle" : "许可证型号",
			"mData" : "equipment_type"
		}, {
			"sTitle" : "许可证序列号",
			"mData" : "equipment_serial_number"
		},{
			"sTitle" : "操作",
			"mData" : "licence_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				
			return '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbLicence_view_' + data + '" href="configuration/cmdbLicence/view?licence_id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbLicence_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_licence&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});