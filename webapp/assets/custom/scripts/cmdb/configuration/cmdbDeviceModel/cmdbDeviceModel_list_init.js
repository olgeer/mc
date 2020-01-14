define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "设备型号",
			"mData" : "device_model"
		},{
			"sTitle" : "设备类型",
			"mData" : "device_type",
			"mRender" : function(data, type, full){
				if(data === 0) {
					return '<span > 服务器 </span>';
				}
				if(data==1) {
					return '<span > 网络设备 </span>';
				}if(data==2) {
					return '<span > 其它设备 </span>';
				}
				if(data==3) {
					return '<span > 存储设备 </span>';
				}
			}
		}, {
			"sTitle" : "设备高度U数",
			"mData" : "device_height"
		}, {
			"sTitle" : "合计-CPU（内核）",
			"mData" : "cpu_total_core"
		},{
			"sTitle" : "合计-内存容量(GB)",
			"mData" : "memory_total"
		},{
			"sTitle" : "合计-硬盘容量(GB)",
			"mData" : "disk_capacity_total"
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_device_model_edit_' + data + '" href="configuration/cmdb_device_model/' + data + '?displayName=型号"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbDeviceModel_view_' + data + '" href="configuration/cmdbDeviceModel/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbDeviceModel_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_device_model&_action=get", getAoData(params));
	};


	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});