define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "设备编号",
			"mData" : "device_num"
		}, {
			"sTitle" : "设备名称",
			"mData" : "device_name"
		}, {
			"sTitle" : "VC名称",
			"mData" : "vc"
		}, {
			"sTitle" : "接口",
			"mData" : "port"
		}, {
			"sTitle" : "备注",
			"mData" : "remarks"
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_network_device_edit_' + data + '" href="configuration/network_device/' + data + '?displayName=网络设备"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_network_device_view_' + data + '" href="configuration/network_device/view?id=' + data + '"><i class="fa fa-eye"></i> 设备详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=network_device&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});